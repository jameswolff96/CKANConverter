# CKANConverter
Converts .ckan files created by [CKAN](http://bit.ly/1EnKq8a) to either .txt or .csv

To use this program download the latest release from [this page](https://github.com/jwolff52/CKANConverter/releases) and follow the steps below for your Operating System:

##Windows 
Unzip the .zip you downloaded to any directory then double click convert.bat

A command prompt will open and ask you for some info, once provided the program will convert your file then exit.

##Linux/Mac 
Unpackage the .zip file

Open a terminal (Ctrl+Alt+T on Linux) and navigate to the folder where you unpackaged the .zip

Type the following into the console

    $ bash convert.sh

##Other Info
Alternatively you can pass all of the requested info on the command line using the following syntax

    $ java -jar CKANConverter.jar "inputPath" "outputPath" [-c|-t]
    
Ensure that the paths are in quotes, especially if there are spaces in the path names.

The last two parameters are optional, however if you do not specify -c (CSV) it will default to -t (Text)