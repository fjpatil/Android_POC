# MVP+Dagger+Retrofit+RxJava
Created the app by using MVP, Dagger 2, RxJava and Retrofit 2

I have covered all the scenarios proided in the task, please have a look at following specifications:
# Specification
Create an Android app which:
1. Ingests a json feed from https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json
 The feed contains a title and a list of rows.
 You can use a third party json parser to parse this if desired.
2. Displays the content (including image, title and description) in a ListView
 The title in the ActionBar should be updated from the json data.
 Each row should be dynamically sized to the right height to display its content - no clipping, no extraneous white-space etc. This means some rows will be larger than others.
3. Loads the images lazily (don’t download them all at once, but only as needed).
4. Implement a refresh function allowing the data & view to be updated
 Use either a refresh button or pull down to refresh.
5. Should not block UI when loading the data from the json feed.