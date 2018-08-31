var loginValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-login .input-requirements small:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return input.value.length < 3 | input.value.length > 50;
        },
        invalidityMessage: 'This input needs to be between 3 and 50 characters',
        element: document.querySelector('#input-login .input-requirements small:nth-child(2)')
    }
];

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

var passwordValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-password .input-requirements small:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return input.value.length < 6 | input.value.length > 50;
        },
        invalidityMessage: 'This input needs to be between 6 and 50 characters',
        element: document.querySelector('#input-password .input-requirements small:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/[0-9]/g);
        },
        invalidityMessage: 'At least 1 number is required',
        element: document.querySelector('#input-password .input-requirements small:nth-child(3)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/[a-z]/g);
        },
        invalidityMessage: 'At least 1 lowercase letter is required',
        element: document.querySelector('#input-password .input-requirements small:nth-child(4)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/[A-Z]/g);
        },
        invalidityMessage: 'At least 1 uppercase letter is required',
        element: document.querySelector('#input-password .input-requirements small:nth-child(5)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/[\!\@\#\$\%\^\&\*]/g);
        },
        invalidityMessage: 'You need one of the required special characters',
        element: document.querySelector('#input-password .input-requirements small:nth-child(6)')
    }
];

var password2ValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-password2 .input-requirements small:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return input.value.length < 6 | input.value.length > 50;
        },
        invalidityMessage: 'This input needs to be between 6 and 50 characters',
        element: document.querySelector('#input-password2 .input-requirements small:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            return input.value !== passwordInput.value;
        },
        invalidityMessage: 'This password needs to match the first on',
        element: document.querySelector('#input-password2 .input-requirements small:nth-child(3)')
    }
];

var loginInput = document.getElementById('loginInput');
loginInput.CustomValidation = new CustomValidation();
loginInput.CustomValidation.validityChecks = loginValidityChecks;

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

var passwordInput = document.getElementById('passwordInput');
passwordInput.CustomValidation = new CustomValidation();
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;

var password2Input = document.getElementById('password2Input');
password2Input.CustomValidation = new CustomValidation();
password2Input.CustomValidation.validityChecks = password2ValidityChecks;