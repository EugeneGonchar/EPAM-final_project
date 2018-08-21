var loginValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-login .input-requirements small:nth-child(1)')
    }
];

var passwordValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length === 0;
        },
        invalidityMessage: 'This input needs not to be empty',
        element: document.querySelector('#input-password .input-requirements small:nth-child(1)')
    }
];

var loginInput = document.getElementById('loginInput');
loginInput.CustomValidation = new CustomValidation();
loginInput.CustomValidation.validityChecks = loginValidityChecks;

var passwordInput = document.getElementById('passwordInput');
passwordInput.CustomValidation = new CustomValidation();
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;

