(function() {
    'use strict';

    angular
        .module('extraBasicApp')
        .controller('ActionDialogController', ActionDialogController);

    ActionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Action', 'Record'];

    function ActionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Action, Record) {
        var vm = this;

        vm.action = entity;
        vm.clear = clear;
        vm.save = save;
        vm.records = Record.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.action.id !== null) {
                Action.update(vm.action, onSaveSuccess, onSaveError);
            } else {
                Action.save(vm.action, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('extraBasicApp:actionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
