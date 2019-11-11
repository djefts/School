/**
 *
 * @author dgb
 */
public class OurListTester 
{
    public static void main(String[] args)
    {
        OurList<Integer> list = new OurList();
        
        for(int i = 1; i <= 10; i++)
        {
            list.add(i);
        }
        
        dumpList(list);
        
        System.out.println("\nEmptying list the hard way");
        while(list.size() != 0)
        {
            System.out.println(list.remove(0));
        }
        
        dumpList(list);
    }//end main
    
    public static void dumpList(OurList<Integer> list)
    {
        System.out.println("Number of nodes: " + list.size());
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }        
    }
}// end OurListTester
