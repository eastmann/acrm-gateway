export default {
    isEmpty(val) {
        return ((val == null) || (val === '') || (val === 'UNDEFINED'));
    },

    validateCompanyParametrs(newUser){
        if (this.isEmpty(newUser.regCompany) || this.isEmpty(newUser.regInn)) {
            return "Выберите компанию из списка";
        }

        return "";
    },

    validateCompany(val, newUser) {
        if (this.isEmpty(val)) {
            return "Укажите наименование компании";
        }

        return this.validateCompanyParametrs(newUser)
    },

    validateEmail(val) {
        if (this.isEmpty(val)) {
            return "Укажите email";
        }

        var re = /\S+@\S+\.\S+/;
        if (!re.test(val)){
            return "Указан некорректный email";
        }

        return "";
    },

    validatePassword(val) {
        if (this.isEmpty(val)) {
            return "Укажите пароль";
        }

        if (val.trim().length <= 0) {
            return "Пароль не может содержать одни пробелы";
        }

        if (val.length < 4) {
            return "Слишком короткий пароль";
        }

        return "";
    },

    validatePassword2(val, pass1) {
        if (this.isEmpty(val)) {
            return "Укажите подтверждение пароля";
        }

        if (val !== pass1) {
            return "Пароль и подтверждение пароля должны совпадать";
        }

        return "";
    },

    validateInn(val) {
        if (this.isEmpty(val)) {
            return "Укажите ИНН";
        }

        return "";
    },

    validateFio(val) {
        if (this.isEmpty(val)) {
            return "Укажите ФИО сотрудника";
        }

        if (val.trim().length <= 0) {
            return "ФИО не может содержать одни пробелы";
        }

        return "";
    },

    validatePhone(val) {
        if (this.isEmpty(val)) {
            return "Укажите телефон";
        }

        if (val.length < 15) {
            return "Номер телефона указан не полностью";
        }

        if (val[0] !== '7'){
            return "Номер телефона должен начинаться с 7";
        }

        return "";
    },

    checkPhone(val) {
        if (this.isEmpty(val)) {
            return "";
        }
        const x = val.replace(/\D/g, '').match(/(\d{0,1})(\d{0,3})(\d{0,3})(\d{0,4})/)
        if (!x[2] && x[1] !== '') {
            if (x[1] == '8'){
                return ''
            }
            return x[1] === '7' ? x[1] : '7' + x[1]
        } else {
            return !x[3] ? x[1] + x[2] : x[1] + '(' + x[2] + ') ' + x[3] + (x[4] ? '-' + x[4] : '')
        }
    },

    checkFio(val) {
        if (this.isEmpty(val)) {
            return "";
        }
        return val.replace(/[^a-zA-Zа-яА-Я ]/g, '')
    },

}
