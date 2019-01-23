//MICROG LETS GOOO
#include <Servo.h>

//pins
int slidePin = 5;
int drillPin = 2;

Servo slide;
int right = 120;    //slide clockwise
int left = 60;      //slide counter-clockwise
int sStop = 95;      //slide stop
int outTime = 1000;
int inTime = 1000;

Servo drill;
int dRight = -1000;  //drill clockwise`
int dLeft = 4000;   //drill counter-clockwise
int dStop = 1500;   //drill stop
int drillTime(1000);
int shimmy = 500;

void setup() {
  Serial.begin(9600);
  Serial.println("begin");

  slide.attach(slidePin);
  drill.attach(drillPin);
  slide.write(sStop);
  drill.write(dStop);
}

void loop() {
  if(Serial.available()) {
    char input = Serial.read();
    Serial.println(input);
    if(input == 'q') {          //extend
      extendSlide();
    } else if(input == 'w') {   //stop
      stopSlide();
    } else if(input == 'e') {   //loose
      retractSlide();
    } else if(input == 'a') {   //drill the other way
      runDrill();
    } else if(input == 's') {   //drill stop
      stopDrill();
    } else if(input == 'd') {   //drill one way
      nurDrill();
    } else if(input == 'p') {   //prep?
      prepDrill();
    }
  }
}

//slide methods
void extendSlide() {
  //clockwise
  slide.write(right);
  delay(50);
}
void retractSlide() {
  //counterclockwise
  slide.write(left);
  delay(50);
}
void stopSlide() {
  slide.write(sStop);
  delay(50);
}

//drill methods
void prepDrill() {
 int x = 0;
 while (x < 15){
  drill.write(dRight);
  delay(shimmy);
  drill.write(dLeft);
  delay(shimmy);
  x = x + 1;
 }
  drill.write(dStop);
  extendSlide();
  delay(200);
  stopSlide();
  stopDrill();
}
void runDrill() {
  drill.write(dLeft);
  delay(50);
}
void nurDrill() {
  drill.write(dRight);
  delay(50);
}
void stopDrill() {
  drill.write(dStop);
  delay(50);
}

