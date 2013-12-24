# My People Jenkins Plugin

Sends the build result to Daum My People messenger (https://mypeople.daum.net/mypeople/web/pr/publicRelation.do). You need your own bot.

##Setup

1. Create your own My People bot (https://dna.daum.net/myapi/authapi/mypeople)
2. Find out the buddy ID to receive build notification using callback function (http://dna.daum.net/apis/mypeople/ref#pushmessage)
3. Install this plugin and Instant Messaging plugin
4. Add the apikey in Jenkins Setting > System Setting > Daum My People Messaging Notifications
5. Add the buddy id in Job Setting > post-build action > My People Messenger


##v0.2

Based on Instant Messaging Plugin (https://wiki.jenkins-ci.org/display/JENKINS/Instant+Messaging+Plugin)


##v0.1

Sends notification when a job fails.
