// cf. https://docs.angularjs.org/tutorial/step_11 (REST and Custom Services)
// cf. https://docs.angularjs.org/api/ngResource/service/$resource (official $resource documentation)

// HINT: ngResource bezieht sich auf angular-resource.js
var posthornServices = angular.module('posthornServices', ['ngResource']);

posthornServices.factory('Mail', ['$resource',
    function($resource){
        return $resource('/rest/v1/mail/:mailId', {mailId:'@indexMailId'}, {}, {stripTrailingSlashes: false});
    }]);

// NOTE: allows for new Mail({a:'b'}).$save(); or mail.$delete(); or Mail.$get({mailId:'abc'});