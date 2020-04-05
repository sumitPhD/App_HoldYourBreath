Hold Your Breath 
I made this app in the direction of Self Assement Test for respiratory problems.
It might be useful in detection of Covid-19 also. Although I am not claiming it.

As Coronavirus affects the respiratory system, monitoring your breathing capacity might help
in detction of the disease. One need to monitor their breath holding capacity few times a day, 
and see if over the period of time the breathing capacity remains same or changes.

The codes and the app is made avaiable in the github repo. 
I would be happy if someone can use it for further improvments or integrate in their own app. 
One can also think in making some app in the direction of Self Assement Test.


Objective 
The idea is to get some quatitive value of a person's breath holding capacity, and it varies from person to person.
So one has to monitor his/her own breath holding capacity over the period of time. If there is some major fluctuations then it might 
indicate some respiratory illness. 

Working of the App
One has to first inhhale and the click the button "INHALE AND HOLD YOUR BREATH", and then the chronometer (clock) starts running.
You have to keep hold your breath. and at the point, you can not hold your breath any longer, click "EXHALE".

There are 3 tests and by clicking "SAVE YOUR TEST" , it takes average of all 3 tests and save it in database. You can see it by clicking
"VIEW ALL TEST RESULTS". The entries also take note of date and time.
One can view bar chart to monitor the all the average of previous set of tests.

One can also set reminder by clicking "SET ALARM TO REMIND YOU".

I purposefully not added functionality of discarding/updating whole database or set of test, as one can cheat on results.
But if doing a test one is not clicked the desired button. or exhale before clicking the button etc. so for, current test I have added
the functionality of discarding the current test on "DISCARD THE LAST TEST".


working APK
https://github.com/sumitPhD/App_HoldYourBreath/blob/master/HoldYourBreath_commit13.apk

Screenshots of App
https://github.com/sumitPhD/App_HoldYourBreath/blob/master/FrontPage_mainActivity.png
https://github.com/sumitPhD/App_HoldYourBreath/blob/master/Test1_completed_Test2_going_on.png
https://github.com/sumitPhD/App_HoldYourBreath/blob/master/average_of_3_test_and_save_it_to_database.png
https://github.com/sumitPhD/App_HoldYourBreath/blob/master/bar_chart_of_avg_of_tests.png
https://github.com/sumitPhD/App_HoldYourBreath/blob/master/option_to_set_alarm_for_reminder.png
