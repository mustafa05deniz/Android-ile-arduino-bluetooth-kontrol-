#include <SoftwareSerial.h>
 
SoftwareSerial mySerial(10, 11);
int dataFromBT;
 
void setup() {
  Serial.begin(9600);
  Serial.println("LEDOnOff Starting...");
 
  // The data rate for the SoftwareSerial port needs to 
  // match the data rate for your bluetooth board.
  mySerial.begin(9600);
  pinMode(7, OUTPUT);   
}
 
void loop() {
  if (mySerial.available())
    dataFromBT = mySerial.read();
 
  if (dataFromBT == '0') {
    // Turn off LED
    digitalWrite(7, LOW);
  } else if (dataFromBT == '1') {
    // Turn on LEFD
    digitalWrite(7, HIGH);
  }
}
