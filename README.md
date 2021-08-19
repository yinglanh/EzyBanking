Ezybanking
=================

A bank app illustrating Android development practices with Android BottonNavigationView.

Introduction
------------

There are three fragemnts in this app, Accounts, Top Up and Transactions. [Room](https://developer.android.com/jetpack/androidx/releases/room) is used for data storage and aa simple MVVM architecture is used.

Getting Started
------------
This project uses the Gradle build system. To build this project, use the
`gradlew build` command or use "Import Project" in Android Studio.

Accounts
------------
You can check the balance, withdraw money in this fragment. And it can also navigate to the other two fragemnts. 
![image](https://user-images.githubusercontent.com/48505160/130052743-f777192d-a8dc-4d8d-bf79-71de988b76c8.png)


Top Up
------------
You can top up balance in this fragment. A message will show up after submitting the payment. 
![image](https://user-images.githubusercontent.com/48505160/130052790-9e9dbd65-339b-4522-9ed0-182927182b67.png)


Accounts
------------
You can check the transactions in this fragment. Livedata and viewmodel are used to monitor transactions so any update will be notified. 
![image](https://user-images.githubusercontent.com/48505160/130053196-f9e528a7-e532-4169-a21a-8330a4faa945.png)

