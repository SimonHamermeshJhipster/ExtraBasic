(function() {
    'use strict';

    angular
        .module('extraBasicApp')
        .controller('ActionDetailController', ActionDetailController);

    ActionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Action', 'Record'];

    function ActionDetailController($scope, $rootScope, $stateParams, previousState, entity, Action, Record) {
        var vm = this;

        vm.action = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('extraBasicApp:actionUpdate', function(event, result) {
            vm.action = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
