A physical sequential text file is comma delimited with a number of fields, either String, Date, int, or double types 
in any combination

A record is an instance of one row or line in the file, a record has a list of fields 

A recordList is a list of records in memory 

A recordViewer is abstract, allowing the recordList to be viewed and edited 

A graphic_recordViewer is a GUI Panel to view/edit a record

A console_recordViewer is a menu driven viewer/editor of a record

A fileManager contains a recordList and assigns a recordViewer

A fileApplication controls the actions of a fileManager, initializes and controls the flow of the fileManager

main instantiates a fileApplication and starts it with a special config. 
