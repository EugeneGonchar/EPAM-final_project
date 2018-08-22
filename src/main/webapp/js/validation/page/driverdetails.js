var nameValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-name .input-requirements small:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return input.value.length < 1 | input.value.length > 100;
        },
        invalidityMessage: 'This input needs to be between 1 and 100 characters',
        element: document.querySelector('#input-name .input-requirements small:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^[A-Za-z\-\s]+$/g);
        },
        invalidityMessage: 'This input needs to contain only lower- or uppercase letters',
        element: document.querySelector('#input-name .input-requirements small:nth-child(3)')
    }
];

var surnameValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-surname .input-requirements small:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return input.value.length < 1 | input.value.length > 100;
        },
        invalidityMessage: 'This input needs to be between 1 and 100 characters',
        element: document.querySelector('#input-surname .input-requirements small:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^[A-Za-z\-\s]+$/g);
        },
        invalidityMessage: 'This input needs to contain only lower- or uppercase letters',
        element: document.querySelector('#input-surname .input-requirements small:nth-child(3)')
    }
];

var emailValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-email .input-requirements small:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return input.value.length > 100;
        },
        invalidityMessage: 'This input needs to be 100 characters maximum',
        element: document.querySelector('#input-email .input-requirements small:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^(?!(?:(?:\x22?\x5C[\x00-\x7E]\x22?)|(?:\x22?[^\x5C\x22]\x22?)){255,})(?!(?:(?:\x22?\x5C[\x00-\x7E]\x22?)|(?:\x22?[^\x5C\x22]\x22?)){65,}@)(?:(?:[\x21\x23-\x27\x2A\x2B\x2D\x2F-\x39\x3D\x3F\x5E-\x7E]+)|(?:\x22(?:[\x01-\x08\x0B\x0C\x0E-\x1F\x21\x23-\x5B\x5D-\x7F]|(?:\x5C[\x00-\x7F]))*\x22))(?:\.(?:(?:[\x21\x23-\x27\x2A\x2B\x2D\x2F-\x39\x3D\x3F\x5E-\x7E]+)|(?:\x22(?:[\x01-\x08\x0B\x0C\x0E-\x1F\x21\x23-\x5B\x5D-\x7F]|(?:\x5C[\x00-\x7F]))*\x22)))*@(?:(?:(?!.*[^.]{64,})(?:(?:(?:xn--)?[a-z0-9]+(?:-[a-z0-9]+)*\.){1,126}){1,}(?:(?:[a-z][a-z0-9]*)|(?:(?:xn--)[a-z0-9]+))(?:-[a-z0-9]+)*)|(?:\[(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9][:\]]){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?)))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))\]))$/g);
        },
        invalidityMessage: 'This input needs to contain only lower- or uppercase letters',
        element: document.querySelector('#input-email .input-requirements small:nth-child(3)')
    }
];

var phoneValidityChecks = [
    {
        isInvalid: function(input) {
            return !input.value.match(/\+375\([0-9]{2}\)[0-9]{3}-[0-9]{2}-[0-9]{2}/g);
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-phone .input-requirements small:nth-child(1)')
    }
];

var nameInput = document.getElementById('nameInput');
nameInput.CustomValidation = new CustomValidation();
nameInput.CustomValidation.validityChecks = nameValidityChecks;

var surnameInput = document.getElementById('surnameInput');
surnameInput.CustomValidation = new CustomValidation();
surnameInput.CustomValidation.validityChecks = surnameValidityChecks;

var emailInput = document.getElementById('emailInput');
emailInput.CustomValidation = new CustomValidation();
emailInput.CustomValidation.validityChecks = emailValidityChecks;

var phoneInput = document.getElementById('phoneInput');
phoneInput.CustomValidation = new CustomValidation();
phoneInput.CustomValidation.validityChecks = phoneValidityChecks;
