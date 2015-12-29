

void setup() {
  Serial.begin(9600);
  pinMode(2,INPUT);
  pinMode(3,OUTPUT);

}

void loop() {

  Serial.println(digitalRead(2));

  if(digitalRead(2)== 0 ){

    digitalWrite(3,HIGH);
    }
  else{
    digitalWrite(3,LOW);
  }

}
