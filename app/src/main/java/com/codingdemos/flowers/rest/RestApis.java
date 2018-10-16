package com.codingdemos.flowers.rest;

public class RestApis {

    public interface KarmaGroups {
        // String BaseURl = "http://ddd.krify.com/karmagroup_web/mobapp/";
        // String BaseURl2 = "https://www.karmagroup.com/wp-content/api/";
        String BaseURl = "https://app.karmagroup.com/mobapp/";

        /**
         * GET Request
         */

        // String vacapediaDestinations = "https://vacapedia.glitch.me/destinations";
        // String vacapediaNews = "https://vacapedia.glitch.me/news";

        String vacapediaDestinations = "https://geonknode.herokuapp.com/destinations";
        String vacapediaNews = "https://geonknode.herokuapp.com/newses";

        String hotelReservation = "https://karmagroup-d66ca.appspot.com/app/hotel_reservation/";
        // String getOffers = BaseURl + "getOffers.php";
        String getOffers = BaseURl + "karmaOffers.php";
        String getOffersAndroid = BaseURl + "karmaOffersAndroid.php";
        String homeDestinations = BaseURl + "homeDestinations.php?";
        // String homeDestinationsDev = BaseURl + "dev/homeDestinations.php?";
        String homeDestinationsDev = BaseURl + "v1.8.5/homeDestinations.php?";
        String getKarmaGroups = BaseURl + "getKarmaGroups.php";
        // String getKarmaGroupsDev = BaseURl + "dev/getKarmaGroups.php";
        String getKarmaGroupsDev = BaseURl + "v1.8.5/getKarmaGroups.php";
        String getOdyssey = BaseURl + "getOdyssey.php";
        String getOdysseyDev = BaseURl + "dev/getOdyssey.php";
        String getBeachDetails = BaseURl + "getBeachDetails.php?";
        String getSpaDetails = BaseURl + "getSpaDetails.php?";
        String getRestaurantDetailsDev = BaseURl + "dev/getRestaurantDetails.php?";
        String getBotiqueDetails = BaseURl + "getBotiqueDetails.php?";
        String getSpaProducts = BaseURl + "getSpaProducts.php?";
        String getDestinationDetails = BaseURl + "getDestinationDetails.php?";
        String club = BaseURl + "club.php";
        String getCountries = BaseURl + "getCountries.php";
        String getMobileCodes = BaseURl + "getMobileCodes.php";
        String contactus = BaseURl + "contactus.php";
        String newcontactus = BaseURl + "newcontactus.php?";
        String getBenefits = BaseURl + "getBenefits.php";
        String getFeeds = BaseURl + "getFeeds.php";
        String getFeedDetails = BaseURl + "getFeedDetails.php";
        String newsFeedEnquiry = BaseURl + "newsFeedEnquiry.php";
        String bookingEnquiry = BaseURl + "bookingEnquiry.php";
        String getRestaurants = BaseURl + "getRestaurants.php";
        String getSpa = BaseURl + "getSpa.php";
        // String getRestaurants = BaseURl + "getRestaurantTest.php";
        // String getSpa = BaseURl + "getSpaTest.php";
        String getIBE = "https://app.karmagroup.com/mobapp/IBE.php";
        // String getGeoIp = "http://freegeoip.net/json/";
        String getGeoIp = "http://www.geoplugin.net/json.gp";
        // String getCountryCode = BaseURl + "getCountryCode.php";
        String getCountryCode = "https://app.karmagroup.com/mobapp/getCountryCode.php";
        String viewPointWeb = "https://karma-test.viewpointweb.com/";
        String viewPointWebVersion1 = "v1/";
        String chargesBridge = "https://karmagroup-d66ca.appspot.com/charges";
        // String destinationsOdyssey = "https://karmagroup-d66ca.appspot.com/destinations/";
        //  destinationsNonMemberOdyssey = "https://karmagroup-d66ca.appspot.com/destinations/non-member";
        String destinationsOdyssey = "https://app.karmagroup.com/mobapp/getDestinations.php";
        String destinationsNonMemberOdyssey = "https://app.karmagroup.com/mobapp/getDestinations.php";
        String getBookingRequest = "https://karmagroup-d66ca.appspot.com/bookingDetails/";
        String getRestCountries = "https://restcountries.eu/rest/v2/all";
        String getActiveMemberInfo = "https://karmagroup-d66ca.appspot.com/v2/login/";
        String getActiveMemberInfoV3 = "https://karmagroup-d66ca.appspot.com/v3/login/";
        // String getHotDeals = "https://karmagroup-d66ca.appspot.com/getHotDeals";
        // String getHotDeals = "https://karmawarehouse.izenoondemand.com/production/api/trial/odysseyhotdeals.php";
        // String getHotDealsHml = "https://karmawarehouse.izenoondemand.com/production/api/trial/odysseyhotdealshml.php";
        String getHotDeals = "https://karmagroup-d66ca.appspot.com/home/hotdeals/";
        String getKarmaBeach = "https://karmawarehouse.izenoondemand.com/production/api/trial/KarmaApp-API/get-karma-beach-cal.php";
        String getInternalResorts = "https://karmagroup-d66ca.appspot.com/getInternalBooking";
        String getExternalResorts = "https://karmagroup-d66ca.appspot.com/getExternalBooking";
        String getDestinationsOdyssey = "https://app.karmagroup.com/mobapp/homeOdysseyDestinations.php";
        String getDestinationsOdysseyDev = "https://app.karmagroup.com/mobapp/dev/homeOdysseyDestinations.php";
        // String getHotelReservation = "https://app.karmagroup.com/mobapp/v1.8.3/getHotelReservation.php";
        String getHotelReservation = "https://app.karmagroup.com/mobapp/v1.8.5/getHotelReservation.php";
        String getContactByCountryTypeIvo = "https://karmagroup-d66ca.appspot.com/appContacts/ivo";
        String getContactByCountryTypeIn = "https://karmagroup-d66ca.appspot.com/appContacts/in";
        String getContactByCountryTypeRu = "https://karmagroup-d66ca.appspot.com/appContacts/ru";
        String getContactByCountryTypeEu = "https://karmagroup-d66ca.appspot.com/appContacts/eu";

        /**
         * POST REQUEST
         */
        String addNote = "https://geonknode.herokuapp.com/notes";

        String updateFavourite = BaseURl + "updateFavourite.php";
        String getFavourites = BaseURl + "getFavourites.php";
        String submit = BaseURl + "submit.php";
        String newsletter = BaseURl + "newsletter.php";
        String newsletterNew = "https://karmagroup-d66ca.appspot.com/newsletter";
        String karmawarehouse = "https://karmawarehouse.izenoondemand.com/production/api/karmaclub/group/karmacluboffer.php";
        String contactDetails = "https://karmamobileapps.appspot.com/tsauto";
        String contactDetailsDev = "https://karmamobileapps.appspot.com/tsauto3in1";

        /**
         * POST(JSON object)
         */
        String bookingRequest = "https://karmagroup-d66ca.appspot.com/bookingDetails/";
        String submitRequest = "https://karmagroup-d66ca.appspot.com/submitRequest/";
        String invite = BaseURl + "invite.php";
        String restaurantReservation = BaseURl + "restaurantReservation.php";
        String spaReservation = BaseURl + "spaReservation.php";
        String restaurantReservationOdyssey = "https://karmagroup-d66ca.appspot.com/restaurantReservation";
        String spaReservationOdyssey = "https://karmagroup-d66ca.appspot.com/spaReservation";
        String contactUsOdyssey = "https://karmagroup-d66ca.appspot.com/contactUs";
        // String restaurantReservation = BaseURl + "restaurantReservationTest.php";
        // String spaReservation = BaseURl + "spaReservationTest.php";
        String geocoding = "https://karmagroup-d66ca.appspot.com/geocoding";

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
        String terms = "https://karmagroup.com/";
        String karma_submit_request = "https://karmagroup.com/ajax_booking_form_submit.php";

        // String news_api = "https://karmagroup.com/news-json/";
        String news_api = "https://app.karmagroup.com/mobapp/getAllNews.php";
        String base_weather_api = "http://api.openweathermap.org/data/2.5/weather?";
        String weather_api = base_weather_api + "lat=xxxx&lon=yyyy&units=metric&type=like&APPID=b258f8d20bbf07f5e1fc03246890e67f";
        String old_login = "https://maestrolive.karmagroup.com/MaxApis/memberlogin?MemberNo=";

        // String book_now_Karma_Bavaria_Germany = "https://www.thebookingbutton.co.uk/properties/karmabavaria?";
        String book_now_Karma_Bavaria_Germany = "https://uk4.roomlynx.net/rezrooms2/loadOBMApplication.action?siteId=KARMABAV&request_locale=en";
        String book_now_Karma_Jimbaran_Bali = "https://www.thebookingbutton.com.au/karmagroup/properties/kjimbarandirect?";
        String book_now_Karma_Kandara_Bali = "https://www.thebookingbutton.com.au/karmagroup/properties/karmakandaradirect?";
        // String book_now_Karma_Rottnest_Australia = "https://www.thebookingbutton.com.au/karmagroup/properties/karmarottnestdirect?";
        // String book_now_Karma_Rottnest_Australia = "https://karmagroup.com/securebooking/karma-rottnest/?";
        String book_now_Karma_Rottnest_Australia = "https://uk4.roomlynx.net/rezrooms2/loadOBMApplication.action?siteId=KARMAROT&chainAction=newAvailabilitySearch&request_locale=en";
        // https://uk4.roomlynx.net/rezrooms2/loadOBMApplication.action?siteId=KARMAROT&chainAction=newAvailabilitySearch&request_locale=en&arrival=28/08/2018&numberOfNights=3&numberOfPersons=3&numberOfChildren=0&offerCode=
        String book_now_Karma_Reef_Indonesia = "https://apac.littlehotelier.com/properties/karma-reef?utf8=%E2%9C%93&locale=en&promotion_code=MEMBERBOOKING&currency=USD";
        // String book_now_Karma_St_Martins_Isles_of_Scilly = "https://www.thebookingbutton.co.uk/properties/karmastmartindirect?";
        String book_now_Karma_St_Martins_Isles_of_Scilly = "https://uk4.roomlynx.net/rezrooms2/loadOBMApplication.action?siteId=KARMASTM&request_locale=en";
        String book_now_Karma_Royal_Haathi_Mahal_Goa = "https://secure.staah.com/common-cgi/properties/b2c/Booknow.pl?action=showpage&amp;PropertyId=968&Ln=&cur=&source=&";
        // String book_now_Karma_Sanctum_Soho = "https://karmagroup.com/booking-sanctum/";
        String book_now_Karma_Sanctum_Soho = "https://uk2.roomlynx.net/rezrooms2/loadOBMApplication.action";
        String book_now_Karma_Sanctum_Green = "https://uk2.roomlynx.net/rezrooms2/loadOBMApplication.action";

        String karma_terms_link = "https://karmaclub.com/terms#wdkzgdPQzKotRgQo.97";
        String membership_benefits_link = "https://www.karmaclub.com/benefits";
        String social_Facebook_link = "https://www.facebook.com/karmagroupglobal/";
        String social_Instagram_link = "https://www.instagram.com/karma.group/";
        String social_Twitter_link = "https://twitter.com/KarmaResorts";
        String social_Youtube_link = "https://www.youtube.com/user/karmaresorts";
        String social_GooglePlus_link = "https://plus.google.com/+Karmaresorts";
        String social_Snapchat_link = "https://www.snapchat.com/add/karmabeachclubs";
        String social_Soundcloud_link = "https://soundcloud.com/karmamusiccollection";

        String login = "https://karmamobileapps.herokuapp.com/login";
        String register = "https://karmamobileapps.herokuapp.com/register";
        String forgotPassword = "https://karmamobileapps.herokuapp.com/forgotPassword/";
        String getProfile_post = "https://karmamobileapps.herokuapp.com/users/";
        String updateProfile_put = "https://karmamobileapps.herokuapp.com/users/";

        String usetTypeMember = "1";
        String usetTypeGuest = "2";

    }

}
