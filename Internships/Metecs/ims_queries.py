"""
Author:     David Jefts

Script to _connect to the IMS database and retrieve data
Most of the code here was "borrowed" from the existing RENT web-tool, courtesy of Austin Gonzalez
"""

import calendar
import html
import re
import socket
import subprocess
from datetime import datetime, timedelta
from pprint import pprint
from time import sleep
import csv


class IMSQuery:
    """
    Microsoft SQL Database Connection Object
    """
    
    def __init__(self, p_num):
        # constructor init
        self.__tag = "Search "
        self.__tagNumber = 0
        
        """ CREDENTIALS """
        # ENTER YOUR USERNAME AND PASSWORD HERE
        self.__appName = "realm"
        self.__appPassword = "realmusers"
        
        self.p_num = p_num
        
        """ CONNECTION PARAMETERS"""
        self.host_addr = 'commlab.jsc.nasa.gov'  # IMS Database Connection Address
        self.host_port = 9607  # IMS Database Connection Port
        
        self.sock = self._connect()
    
    def _connect(self):
        print("Connecting to IMS database")
        
        if self.host_addr is None or self.host_port is None:
            raise Exception('ENTER THE CONNECTION ADDRESS INFORMATION IN ORDER TO CONNECT TO THE IMS DATABASE')
        
        # create socket object
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        
        # setup server address and _connect to it
        server_address = (self.host_addr, self.host_port)
        sock.settimeout(5)
        sock.connect(server_address)
        sock.settimeout(None)
        return sock
    
    def disconnect(self):
        # self.request_disconnect(self.sock)
        self.sock.close()
    
    def get_tag(self):
        # Change tag after each request
        self.__tagNumber = self.__tagNumber + 1
        self.__tag = "Search " + str(self.__tagNumber)
        return self.__tag
    
    def request_make_register(self, sock):
        # Initializes connection with App credentials
        
        print("Attempting to register with IMS database")
        req = """
        <OpenIMS>
            <Register ClientTag=""" + '"' + self.get_tag() + '"' + """>
            <ApplicationCredentials>
                <ApplicationName>""" + self.__appName + """</ApplicationName>
                <ApplicationPassword>""" + self.__appPassword + """</ApplicationPassword>
            </ApplicationCredentials>
            <UserCredentials>
                <UserName></UserName>
                <UserPassword></UserPassword>
            </UserCredentials>
            </Register>
        </OpenIMS>
        """
        # print(req)
        return self._handle_request(sock, req.encode())
    
    def request_items_with_PartNumber(self, sock, p_num):
        # Submit request for list of items with the input part number
        print("Requesting list of items with PartNumber '" + p_num + "'. This takes a bit so go grab some coffee.")
        req = """
        <OpenIMS>
            <Search>
                <SearchStatement>
                    <SearchTerm>
                        <FieldName>
                            PartNumber
                        </FieldName>
                        <LogicOperator>
                            =
                        </LogicOperator>
                        <SearchValue>
                            """ + p_num + """
                        </SearchValue>
                    </SearchTerm>
                </SearchStatement>
            </Search>
        </OpenIMS>
        """
        # print(req)
        return self._handle_request(sock, req.encode())
    
    def request_item_data(self, sock, barcode):
        # Submit request for item data using a PART NUMBER
        """
        Searching for these item properties:
                        EnglishName
        PART NUMBER     PartNumber
        STATUS          Status
        S/N             SerialNumber
        OPNOM           OpNom
        BARCODE         Barcode
                        StowageDomain
        LOCATION        ItemLocation
        NOTES           Notes
        LAUNCH          Launch
        """
        req = """
        <OpenIMS>
            <GetItemProperties ClientTag=""" + '"' + self.get_tag() + '"' + """>
                <ItemUniqueID>
                    <Barcode>
                    """ + barcode + """
                    </Barcode>
                </ItemUniqueID>
                <FieldsRequested>
                    <EnglishName/>
                    <PartNumber/>
                    <Status/>
                    <SerialNumber/>
                    <OpNom/>
                    <Barcode/>
                    <StowageDomain/>
                    <ItemLocation/>
                    <Notes/>
                    <Launch/>
                </FieldsRequested>
            </GetItemProperties>
        </OpenIMS>
        """
        return self._handle_request(sock, req.encode())
    
    def request_disconnect(self, sock):
        # Request to disconnect from the IMS database
        
        req = """
        <OpenIMS>
            <Disconnect/>
        </OpenIMS>
        """
        # print(req)
        return self._handle_request(sock, req.encode())
    
    def _handle_request(self, sock, xml_request):
        # Method handler to make requests and recv data
        
        sock.sendall(xml_request)
        data = ''
        start = datetime.utcnow()
        current = datetime.utcnow()
        dt = current - start
        while dt.seconds < 120:
            # Retrieve data in 4096 byte blocks until end tag is detected
            block = sock.recv(4096).decode('utf-8', 'ignore')
            data += block
            if re.search('</OpenIMS>', block) or re.search('nIMS>', block):
                break
            
            current = datetime.utcnow()
            dt = current - start
            sleep(0.05)
        if dt.seconds >= 120:
            print("Socket timeout reached, either received malformed request or connection was lost.")
        # print(data)
        return data
    
    def _parse_item_data(self, sock, barcode):
        # Method to submit and parse xml request for item information based on barcode passed in
        """
            Searching for these item properties:
                            EnglishName
            PART NUMBER     PartNumber
            STATUS          Status
            S/N             SerialNumber
            OPNOM           OpNom
            BARCODE         Barcode
                            StowageDomain
            LOCATION        ItemLocation
            NOTES           Notes
            LAUNCH          Launch
        """
        
        # submit xml request for item information
        result = self.request_item_data(sock, barcode)
        # print(result)
        
        # [Name, Part Number, Status, Serial Number, OpNom, Barcode, Stowage Domain, Item Location, Notes, Launch]
        item_data = ["Name", "PartNumber", "Status", "S/N", "OpNom", barcode, "SD", "Loc", "''", "Launch Code"]
        is_error = False
        
        # Check for errors in request
        if re.search('<Error', result):
            is_error = True
            errors = re.findall('<Error ErrorText="(.*)" ErrorCode="(.*)" />', result)
            # print(errors)
            for err in errors:
                print(html.unescape(err[0]), end = ' ')
        if is_error:
            print("\n")
            return None
        
        search_terms = re.findall('<PropertiesBasic>[\s\S]*</PropertiesBasic>', result)
        for search_term in search_terms:
            if re.search('<EnglishName>(.*)</EnglishName>', search_term):
                regex = re.search('<EnglishName>(.*)</EnglishName>', search_term)
                item_data[0] = str(regex.group(1))
            if re.search('<PartNumber>(.*)</PartNumber>', search_term):
                regex = re.search('<PartNumber>(.*)</PartNumber>', search_term)
                item_data[1] = str(regex.group(1))
            if re.search('<StatusCode>(.*)</StatusCode>', search_term):
                regex = re.search('<StatusCode>(.*)</StatusCode>', search_term)
                item_data[2] = str(regex.group(1)) + " -- "
            if re.search('<StatusEnglishText>(.*)</StatusEnglishText>', search_term):
                regex = re.search('<StatusEnglishText>(.*)</StatusEnglishText>', search_term)
                item_data[2] += str(regex.group(1))
            if re.search('<SerialNumber>(.*)</SerialNumber>', search_term):
                regex = re.search('<SerialNumber>(.*)</SerialNumber>', search_term)
                item_data[3] = str(regex.group(1))
            if re.search('<OpNom>(.*)</OpNom>', search_term):
                regex = re.search('<OpNom>(.*)</OpNom>', search_term)
                item_data[4] = str(regex.group(1))
            if re.search('<Barcode>(.*)</Barcode>', search_term):
                regex = re.search('<Barcode>(.*)</Barcode>', search_term)
                assert str(regex.group(1)) == str(barcode)
            if re.search('<StowageDomainCode>(.*)</StowageDomainCode>', search_term):
                regex = re.search('<StowageDomainCode>(.*)</StowageDomainCode>', search_term)
                item_data[6] = str(regex.group(1)) + '-'
            if re.search('<StowageDomainEnglishText>(.*)</StowageDomainEnglishText>', search_term):
                regex = re.search('<StowageDomainEnglishText>(.*)</StowageDomainEnglishText>', search_term)
                item_data[6] += str(regex.group(1))
            if re.search('<Location>(.*)</Location>', search_term):
                regex = re.search('<Location>(.*)</Location>', search_term)
                item_data[7] = str(regex.group(1))
            if re.search('<Notes>(.*)</Notes>', search_term):
                regex = re.search('<Notes>(.*)</Notes>', search_term)
                item_data[8] = str(regex.group(1))
            if re.search('<LaunchCode>(.*)</LaunchCode>', search_term):
                regex = re.search('<LaunchCode>(.*)</LaunchCode>', search_term)
                item_data[9] = str(regex.group(1))
        return item_data
    
    def get_barcodes(self):
        """
        Public method for gathering a list of barcodes for every Food BOB in NASA's IMS Database
        Queries the database for all objects with the PartNumber "SEG48102042-901"

        :param: None
        :return: List of items that are marked missing
        """
        print("Gathering list of missing items")
        
        # Make Register Request
        self.request_make_register(self.sock)
        
        # Submit XML request to IMS database
        search_results = self.request_items_with_PartNumber(self.sock, self.p_num)
        
        # parse through lost-items data to build list of lost-item barcodes
        print("Building list of barcodes...")
        barcodes = []
        search_terms = re.findall('<SearchItem>[\s\S]*?</SearchItem>', search_results)
        for searchTerm in search_terms:
            if re.search('<Barcode>([A-Z0-9])\w+</Barcode>', searchTerm):
                regex_barcode = re.search('<Barcode>(.*)</Barcode>', searchTerm)
                barcodes.append(regex_barcode.group(1))
        # print(barcodes)
        return barcodes
    
    def gather_item_list_data(self, object_list):
        """
        Public method used to get the IMS database information for each barcode in `object_list`

        :param object_list: string list of IMS item barcodes
        :return: list of items that should have all of the relevant IMS data
                 each item is a list of the format:
            [Name, Part Number, Status, Serial Number, OpNom, Barcode, Stowage Domain, Item Location, Notes, Launch]
        """
        
        # Make Register Request
        self.request_make_register(self.sock)
        
        total = len(object_list)
        if total >= 50:
            print("\nIt's time for a very nice lunch and a walk. You've got", total, "items to pull data for.")
            print("This will take approximately", total * 0.686 / 60, "minutes to complete.")
        
        # create new list with object data
        objects = []
        print("Gathering IMS item information...")
        for barcode in object_list:
            data = self._parse_item_data(self.sock, barcode)
            if data is not None:
                objects.append(data)
            else:
                print("Error with barcode", barcode)
                raise
        return objects


""" SCRIPT TESTING """
if __name__ == "__main__":
    # Part Number, e.g. 'SEG48102042-901'
    part_number = input('\n\nEnter Part Number to search for:\t\t')
    
    ims = IMSQuery(part_number)
    ims.request_make_register(ims.sock)
    
    # items = ims.request_items_with_PartNumber(ims.sock, p_num = "SEG48102042-901")
    # print(items)
    
    barcodes = ims.get_barcodes()
    print('\t', barcodes)
    
    list_of_items = ims.gather_item_list_data(barcodes)
    for item in list_of_items:
        print('[ ' + ', '.join(map(str, item)) + ' ]')
    # Title row
    list_of_items.insert(0, ["Name", "Part Number", "Status", "Serial Number", "OpNom", "Barcode",
                             "Stowage Domain Code - Text", "Location", "Notes", "Launch Code"])
    
    with open('Food_BOBs.csv', 'w+') as my_csv:
        writer = csv.writer(my_csv, delimiter = ';')
        writer.writerows(list_of_items)
    
    ims.disconnect()
