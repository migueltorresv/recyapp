To start the test with the application, you should have an apache server installed with php and mysql locally,
edit the variables $ hostname_localhost, $ database_localhost, $ username_localhost, $ password_localhost with the data of your local server in the scripts (consultUsuario.php, listrObtPoints.php, registerObtPoint.php, registerUsuario.php) found in the "recyapp" found in this thread and copy the entire folder to the root of your local server; For this test the Windows operating system and the WampServer server were used, if you use the same you can go to the address "C: \ wamp64 \ www" and copy it there, for another server like Xampp, it would be "C: \ xampp \ htdocs "and there copy it.

Once the entire "recyapp" folder has been copied, we proceed to edit the resource strings.xml the string "dir_conexion" with your local ip address (localhost) in Android Studio. That would be it for the integration of the RecyApp application.

Note: the RecyApp project consists of 2 parts, the first part is the RecyApp mobile application and an object classification mobile application that is part of the RecyApp smart container, to make use of the object classifier and be able to obtain the qr code when it detects a bottle, paper or a can. The code is developed with Android Studio and Java, this code is located at https://github.com/migueltorresv/MachineRecyApp







