#include <Adafruit_NeoPixel.h>
#include "SPI.h"
#include<Wire.h>

#define numberOfLeds 54
#define dataPin 2

byte lightState;
boolean newState;
int timeCounter = 0;
int delayTime;
int moveBy;
int counterRaindow = 0;
byte patternLength;

Adafruit_NeoPixel stripOne = Adafruit_NeoPixel(numberOfLeds, dataPin, NEO_GRB + NEO_KHZ800);

void setup(){


  stripOne.begin();


  Serial.begin(9600);


  stripOne.show();

  Wire.begin(84);//register address
  Wire.onReceive(receiveEvent);
  Serial.println("register");
  delayTime = 5;
  moveBy = 1;
  patternLength = (stripOne.numPixels()-1)/4;
  lightState = 0;
  newState = true;


}

void loop(){
  if (newState) {
    switch (lightState) {

    case 0:
      whiteState(stripOne);
      break;
      //all white
    case 1:
      blueState(stripOne);
      break;
      //blue
    case 2:
      yellowState(stripOne);
      break;
      //Yellow
    case 3:
      greenState(stripOne);
      break;
      //Green
    case 4:
      redState(stripOne);
      break;
      //Red
    case 5: 
      annoyingRaindow(stripOne);
      break;
      //AR
    case 6:
      simpleRaindow(stripOne);
      break;
    case 7:
      blueStateWithGreen(stripOne);
      break;
    case 8:
      redStateWithGreen(stripOne);
      break;
    case 9:
      redStateWithGreenInverted(stripOne);
      moveBy = -1;
      break;
    case 10:
      blueStateWithGreenInverted(stripOne);
      moveBy = -1;
      break;
    case 11:
      redStateInverted(stripOne);
      moveBy = -1;
      break;
    case 12:
      blueStateInverted(stripOne);
      moveBy = -1;
      break;
    case 13:
      simpleRaindowFade(stripOne);
      
      break;
    default: 
      whiteState(stripOne); 
      break;
    }


    newState = false;
  }

  if (moveBy >= 0){
    shift(moveBy, patternLength, stripOne);
  }
  else
  {
    shiftInverted(moveBy, patternLength, stripOne);
  }
  stripOne.show();

  delay(delayTime);
}

void receiveEvent(int value) {
  while (Wire.available()) {
    lightState = Wire.read(); 
    newState = true;
  }
  Serial.println("receive");
}

void whiteState(Adafruit_NeoPixel &strip)
{

  for (int k = 0; k < strip.numPixels(); k++)
  {
    strip.setPixelColor(k, 255, 255, 255);
  }

}

void redState(Adafruit_NeoPixel &strip)
{
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  delayTime = 10;
  patternLength = (strip.numPixels()-1)/2;
  int buffer = ((255 / strip.numPixels())/2);
  int x = 0;
  while ((redShade < 255) && ((redShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade += buffer;
    x++;
  }
  while ((redShade > 50) && ((redShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade -= buffer;
    x++;
  }
}

void yellowState(Adafruit_NeoPixel &strip)
{
  delayTime = 10;
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  patternLength = (strip.numPixels()-1)/2;
  int buffer = ((255 / strip.numPixels())/2);
  int x = 0;
  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade += buffer;
    greenShade += buffer;
    x++;
  }
  while ((greenShade > 50) && ((greenShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade -= buffer;
    greenShade -= buffer;
    x++;
  }
}

void redStateInverted(Adafruit_NeoPixel &strip)
{
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  delayTime = 10;
  patternLength = (strip.numPixels()-1)/2;
  int buffer = ((255 / strip.numPixels())/2);
  int x = 1;
  while ((redShade < 255) && ((redShade + buffer) <= 255)){
    strip.setPixelColor(strip.numPixels() - x, redShade, greenShade, blueShade);
    redShade += buffer;
    x++;
  }
  while ((redShade > 50) && ((redShade - buffer) >= 50)){
    strip.setPixelColor(strip.numPixels() - x, redShade, greenShade, blueShade);
    redShade -= buffer;
    x++;
  }
}

void greenState(Adafruit_NeoPixel &strip)
{

  for (int k = 0; k < strip.numPixels(); k++)
  {
    strip.setPixelColor(k, 0, 255, 0);
  }  
}

void blueState(Adafruit_NeoPixel &strip)
{
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  patternLength = (strip.numPixels()-1)/2;
  delayTime = 10;
  int buffer = ((255 / strip.numPixels())/2);
  int x = 0;
  while ((blueShade < 255) && ((blueShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade += buffer;
    x++;
  }
  while ((blueShade > 50) && ((blueShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade -= buffer;
    x++;
  }
}

void blueStateInverted(Adafruit_NeoPixel &strip)
{
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  delayTime = 10;
  patternLength = (strip.numPixels()-1)/2;
  int buffer = ((255 / strip.numPixels())/2);
  int x = 1;
  while ((blueShade < 255) && ((blueShade + buffer) <= 255)){
    strip.setPixelColor(strip.numPixels() - x, redShade, greenShade, blueShade);
    blueShade += buffer;
    x++;
  }
  while ((blueShade > 50) && ((blueShade - buffer) >= 50)){
    strip.setPixelColor(strip.numPixels() - x, redShade, greenShade, blueShade);
    blueShade -= buffer;
    x++;
  }
}

void blueStateWithGreen(Adafruit_NeoPixel &strip)
{
  timeCounter++;
  int cF = 2;
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  patternLength = (strip.numPixels()-1)/cF;
  delayTime = 20;
  int buffer = ((255 / strip.numPixels()-1)*5*cF);
  int x = 0;
  int k = 0;

  while ((blueShade < 255) && ((blueShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade += buffer;
    x++;
  }
  while ((blueShade > 50) && ((blueShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade -= buffer;
    x++;
  }
  redShade = 0;
  greenShade = 0;
  blueShade = 0;

  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    strip.setPixelColor(k + (x-1), redShade, greenShade, blueShade);
    greenShade += buffer;
    k++;
  }
  while ((greenShade > 50) && ((greenShade - buffer) >= 50)){
    strip.setPixelColor(k + (x-1), redShade, greenShade, blueShade);
    greenShade -= buffer;
    k++;
  }


}

void redStateWithGreen(Adafruit_NeoPixel &strip)
{
  timeCounter++;
  int cF = 2;
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  patternLength = (strip.numPixels()-1)/cF;
  delayTime = 20;
  int buffer = ((255 / strip.numPixels()-1)*5*cF);
  int x = 0;
  int k = 0;

  while ((redShade < 255) && ((redShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade += buffer;
    x++;
  }
  while ((redShade > 50) && ((redShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade -= buffer;
    x++;
  }
  redShade = 0;
  greenShade = 0;
  blueShade = 0;

  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    strip.setPixelColor(k + (x-1), redShade, greenShade, blueShade);
    greenShade += buffer;
    k++;
  }
  while ((greenShade > 50) && ((greenShade - buffer) >= 50)){
    strip.setPixelColor(k + (x-1), redShade, greenShade, blueShade);
    greenShade -= buffer;
    k++;
  }


}

void redStateWithGreenInverted(Adafruit_NeoPixel &strip)
{

  timeCounter++;
  int cF = 2;
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  patternLength = (strip.numPixels()-1)/cF;
  delayTime = 20;
  int buffer = ((255 / strip.numPixels()-1)*5*cF);
  int x = strip.numPixels()-1;


  while ((redShade < 255) && ((redShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade += buffer;
    x--;
  }
  while ((redShade > 50) && ((redShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade -= buffer;
    x--;
  }
  redShade = 0;
  greenShade = 0;
  blueShade = 0;
  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade += buffer;
    x--;
  }
  while ((greenShade > 50) && ((greenShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade -= buffer;
    x--;
  }


}

void blueStateWithGreenInverted(Adafruit_NeoPixel &strip)
{

  timeCounter++;
  int cF = 2;
  int redShade = 0;
  int greenShade = 0;
  int blueShade = 0;
  patternLength = (strip.numPixels()-1)/cF;
  delayTime = 20;
  int buffer = ((255 / strip.numPixels()-1)*5*cF);
  int x = strip.numPixels()-1;


  while ((blueShade < 255) && ((blueShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade += buffer;
    x--;
  }
  while ((blueShade > 50) && ((blueShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade -= buffer;
    x--;
  }
  redShade = 0;
  greenShade = 0;
  blueShade = 0;
  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade += buffer;
    x--;
  }
  while ((greenShade > 50) && ((greenShade - buffer) >= 50)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade -= buffer;
    x--;
  }


}

void simpleRaindowFade(Adafruit_NeoPixel &strip)
{
  delayTime = 25;
  patternLength = (strip.numPixels()-1)/1;
  int redShade = 255;
  int greenShade = 0;
  int blueShade = 0;
  int buffer = (1530 / patternLength);
  int x = 0;
  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    for ( x = 0; x < strip.numPixels()-1; x++) strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade += buffer;
    
  }
  redShade = 255;
  greenShade = 255;
  blueShade = 0;
  while ((redShade > 0) && ((redShade - buffer) > 0)){
    for ( x = 0; x < strip.numPixels()-1; x++) strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade -= buffer;
    
  }
  redShade = 0;
  greenShade = 255;
  blueShade = 0;
  while ((blueShade < 255) && ((blueShade + buffer) <= 255)){
    for ( x = 0; x < strip.numPixels()-1; x++) strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade += buffer;
    
  }
  redShade = 0;
  greenShade = 255;
  blueShade = 255;
  while ((greenShade > 0) && ((greenShade - buffer) > 0)){
    for ( x = 0; x < strip.numPixels()-1; x++) strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade -= buffer;
    
  }
  redShade = 0;
  greenShade = 0;
  blueShade = 225;
  while ((redShade < 255) && ((redShade + buffer) <= 255)){
    for ( x = 0; x < strip.numPixels()-1; x++) strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade += buffer;
    
  }
  redShade = 255;
  greenShade = 0;
  blueShade = 225;
  while ((blueShade > 0) && ((blueShade - buffer) > 0)){
    for ( x = 0; x < strip.numPixels()-1; x++) strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade -= buffer;
    
  }

}

void simpleRaindow(Adafruit_NeoPixel &strip)
{
  delayTime = 25;
  patternLength = (strip.numPixels()-1)/1;
  int redShade = 255;
  int greenShade = 0;
  int blueShade = 0;
  int buffer = (1530 / patternLength);
  int x = 0;
  while ((greenShade < 255) && ((greenShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade += buffer;
    x++;
  }
  redShade = 255;
  greenShade = 255;
  blueShade = 0;
  while ((redShade > 0) && ((redShade - buffer) > 0)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade -= buffer;
    x++;
  }
  redShade = 0;
  greenShade = 255;
  blueShade = 0;
  while ((blueShade < 255) && ((blueShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade += buffer;
    x++;
  }
  redShade = 0;
  greenShade = 255;
  blueShade = 255;
  while ((greenShade > 0) && ((greenShade - buffer) > 0)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    greenShade -= buffer;
    x++;
  }
  redShade = 0;
  greenShade = 0;
  blueShade = 225;
  while ((redShade < 255) && ((redShade + buffer) <= 255)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    redShade += buffer;
    x++;
  }
  redShade = 255;
  greenShade = 0;
  blueShade = 225;
  while ((blueShade > 0) && ((blueShade - buffer) > 0)){
    strip.setPixelColor(x, redShade, greenShade, blueShade);
    blueShade -= buffer;
    x++;
  }

}

void annoyingRaindow(Adafruit_NeoPixel &strip)
{
  delayTime = 25;
  int greenShade = random(255);
  int redShade = random(255);
  int blueShade = random(255);
  int rangeShade = 255;
  int counter = random(1,3);
  int counterOld = 0;
  for (int k = 0; k < strip.numPixels(); k++)
  {
    if (counter == 1)
    {
      rangeShade = 255;
      redShade = random(255);
      greenShade = random(rangeShade);

      rangeShade -= greenShade;

      blueShade = random(rangeShade);
    }
    if (counter = 2)
    {
      rangeShade = 255;
      greenShade = random(255);
      rangeShade -= redShade;
      blueShade = random(rangeShade);
      rangeShade -= greenShade;
      redShade = random(rangeShade);
    }
    if (counter = 3)
    {
      rangeShade = 255;
      blueShade = random(255);
      rangeShade -= redShade;
      redShade = random(rangeShade);
      rangeShade -= greenShade;
      greenShade = random(rangeShade);

    }
    strip.setPixelColor(k, redShade, greenShade, blueShade);
    counterOld = counter;
    counter = random(1,3);
    while (counterOld == counter) counter = random(1,3);
  }



}


void shift(int offset, int repeat, Adafruit_NeoPixel &strip)
{
  for (int j = 0; j < offset; j++)
  { 
    for (int i = strip.numPixels() - 1; i > 0; i--)
    {
      strip.setPixelColor(i , strip.getPixelColor(i - 1));
    }

    strip.setPixelColor(0, strip.getPixelColor(repeat));
  }
}

void shiftInverted(int offset, int repeat, Adafruit_NeoPixel &strip)
{
  offset = -offset;

  for (int j = 0; j < offset; j++)
  { 
    for (int i = 0; i < strip.numPixels(); i++)
    {

      strip.setPixelColor(i ,strip.getPixelColor(i+1));
    }

    strip.setPixelColor(strip.numPixels() - 1, strip.getPixelColor(strip.numPixels()-1 - repeat));
  }
}















