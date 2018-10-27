package com.codingdemos.vacapedia.rest;

public class RestApis {

    public interface KarmaGroups {

        /**
         * GET Request
         */

        // String vacapediaDestinations = "https://vacapedia.glitch.me/destinations";
        // String vacapediaNews = "https://vacapedia.glitch.me/news";

        String vacapediaDestinations = "https://geonknode.herokuapp.com/destinations";
        String vacapediaNews = "https://geonknode.herokuapp.com/newses";
        String vacapediaNotes = "https://geonknode.herokuapp.com/notes";
        String vacapediaSlides = "https://geonknode.herokuapp.com/slides";
        String vacapediaUsers = "https://geonknode.herokuapp.com/users";
        String vacapediaFamilies = "https://geonknode.herokuapp.com/families";
        String vacapediaNotifications = "https://geonknode.herokuapp.com/notifications";
        String vacapediaPlans = "https://geonknode.herokuapp.com/plans";

        /**
         * POST REQUEST
         */
        String addNote = "https://geonknode.herokuapp.com/notes";

        /**
         * POST(JSON object)
         */

        /**
         * Google Api's
         */
        String google_time_api = "https://maps.googleapis.com/maps/api/timezone/json?";
        String google_time_api_params = "location=xxxx,yyyy&timestamp=0&key=AIzaSyCG-ZUDsV_g445TBTIxpAhb_DM9M71GRI8";
        // String google_time_api_params = "location=xxxx,yyyy&timestamp=0&key=AIzaSyDe048Y3K5pJEsZa7cWNjRSjdFVxKHEPP8";
        // String google_time_api_params = "location=xxxx,yyyy&timestamp=0&key=AIzaSyAT6cY6dg_0KTQtDJ2WCnxLXLxfqKnK6m0";

        /**
         * Client Side api's
         */

    }

}
