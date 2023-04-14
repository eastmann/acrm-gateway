export default {
    round(x, n) {
        if ((x === '') || (x === null) || (x === undefined)){
            return x;
        }

        return Math.round(x * Math.pow(10, n)) / Math.pow(10, n);
    },

    numberWithSpaces(val, n) {
        if ((val === '') || (val === null) || (val === undefined)){
            return '';
        }

        let res = 0;
        // if (typeof val === 'string'){
        //     res = this.round(Number(val.replace(',', '.')), n);
        // } else {
        //     res = this.round(val, n);
        // }
        if (typeof val === 'string'){
            res = Number(val.replace(',', '.')).toFixed(n);
        } else {
            res = val.toFixed(n);
        }
        return this.separator(res);
    },

    separator(val){
        if ((val === '') || (val === null) || (val === undefined)){
            return val;
        }
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ").replace(".", ",");
    },

    numberWithoutSpaces(val) {
        if ((val === '') || (val === null) || (val === undefined)){
            return val;
        }
        if (typeof val === 'string') {
            return Number(val.replaceAll(" ", ""))
        }

        return val;
    }

}