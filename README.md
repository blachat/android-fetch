# Feature - Initial backend API integration 

## App Functionality 

https://github.com/user-attachments/assets/4792837b-d4fe-46c1-bf56-3762c7b42d64



* The initial functionality includes the following:   
  * Adds RetroFit Implementation 
  * Adds Build config field to gradle 
     * allows endpoint calls production server  or develop server based on build type
  * Fetch list of "Fetch" items from the backend
    *  Response is grouped, and sorted
  * Presents user with list of items 
    * List items color will change based on group it belongs to 
  * Allows user to select an item from the list
    * Animate and navigates to details fragment
    * (Can be used to show additional details to the user)
  * Allow user to filter list based on group
    * Filters are dynamically generated based on the data received from the backend 
