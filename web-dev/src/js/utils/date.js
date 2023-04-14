import dateFormat from 'dateformat'

dateFormat.i18n = {
    dayNames: [
        'Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб',
        'Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота'
    ],
    monthNames: [
        'Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июн', 'Июл', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек',
        'Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'
    ],
    timeNames: [
        'a', 'p', 'am', 'pm', 'A', 'P', 'AM', 'PM'
    ]
};

export default {
    formatDefault(date) {
        return dateFormat(date, "dd mmm yyyy, hh:MM:ss")
    },

    formatSimple(date) {
        if (date === null){
            return null;
        }
        return dateFormat(date, "dd-mm-yyyy")
    },

    timeStampToDate(timeStamp) {
        if (timeStamp == null) return null;
        let pDate = new Date(timeStamp);
        return pDate.toISOString().substr(0, 10);
    },

    dateToText(myDate) {
        if (myDate != null) {
            return myDate.split('T')[0];
        }
        return myDate;
    },

    localDateToText(myDate) {
        if (myDate != null) {
            return myDate.toLocaleString().split(',')[0]
        }
        return myDate;
    },

    getNumberOfDays(end) {
        const date1 = new Date();
        const date2 = new Date(end);
        // One day in milliseconds
        const oneDay = 1000 * 60 * 60 * 24;
        // Calculating the time difference between two dates
        const diffInTime = date2.getTime() - date1.getTime();
        // Calculating the no. of days between two dates
        return  Math.round(diffInTime / oneDay);
    }

}