// cf. https://docs.angularjs.org/tutorial/step_02 (Angular Templates)

// HINT: Modulbezeichnung wird referenziert in HTML
var posthornApp = angular.module('posthornApp', ['posthornServices']);

posthornApp.controller('MailListController', function ($scope, $log, Mail) {
    $scope.mails = Mail.query();
});
