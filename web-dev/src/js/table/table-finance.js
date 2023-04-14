import numerUtils from "@/js/utils/numer-utils";

export default {
    ...numerUtils,

    changeFilter(columns, field, newFilter) {
        let found = columns.find((c) => {
            return c.field == field
        });
        if (found) {
            if (found.hasOwnProperty("filterOptions")) {
                if (found.filterOptions.hasOwnProperty("filterValue")) {
                    found.filterOptions.filterValue = newFilter;
                }
            }
        }
    },

    creteColumnsBudgetFront() {
        let columns = [
            {
                text: 'Показатели',
                value: 'name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '40%',
                sortable: true,
                filterable: true,
            },
            {
                text: '2021',
                value: 'year2021',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2022',
                value: 'year2022',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2023',
                value: 'year2023',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
        ];

        return columns;
    },

    creteColumnsBudget4kvFront() {
        let columns = [
            {
                text: 'Показатели',
                value: 'name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '40%',
                sortable: true,
                filterable: true,
            },
            {
                text: '2021',
                value: 'year2021',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2022',
                value: 'year2022',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2023',
                value: 'year2023',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
        ];

        return columns;
    },

    creteColumnsWallet() {
        let columns = [
            {
                text: 'Показатели',
                value: 'name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '40%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2021',
                value: 'year2021',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2022',
                value: 'year2022',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
            {
                text: '2023',
                value: 'year2023',
                thClass: 'rt-header-center',
                tdClass: 'rt-col-center-latter-spacing',
                width: '20%',
                sortFn(x, y) {return x < y ? -1 : x > y ? 1 : 0;},
                sortable: true,
                filterable: true,
            },
        ];

        return columns;
    },

    getName(val){
        let arr = val.split(",");
        return arr[0];
    },

    getMeasure(val){
        if (val === null){
            return val
        }
        let arr = val.split(",");
        if (arr.length <= 1){
            return '';
        }
        return '(' + arr[1].trim() + ')';
    },

    creteRows(holdingMains, dzoId) {
        if (holdingMains === null){
            return null;
        }

        if (dzoId === 0){
            return holdingMains;
        }

        let elems =  holdingMains.filter(x => x.dzoId === dzoId)

        return elems.map((row) => {
                return {
                    "name": `<div>${this.getName(row.name)} <span class="table-finance__measure">${this.getMeasure(row.name)}</span></div>`,
                    "year2023": row.year2023 ? this.numberWithSpaces(row.year2023) : null,
                    "year2022": row.year2022 ? this.numberWithSpaces(row.year2022) : null,
                    "year2021": row.year2021 ? this.numberWithSpaces(row.year2021) : null,
                }
            }
        );
    },

    creteRowsTotal(holdingMains, dzoId) {

        if (holdingMains === null){
            return null;
        }

        let elems =  holdingMains;

        return elems.map((row) => {
                return {
                    "name": `<div>${this.getName(row.name)} <span class="table-finance__measure">${this.getMeasure(row.name)}</span></div>`,
                    "year2023": row.year2023 ? this.numberWithSpaces(row.year2023) : null,
                    "year2022": row.year2022 ? this.numberWithSpaces(row.year2022) : null,
                    "year2021": row.year2021 ? this.numberWithSpaces(row.year2021) : null,
                }
            }
        );
    },

    getKeyProjectDesc(holdingMains, dzoId) {
        if (holdingMains === null){
            return null;
        }

        if (dzoId === 0){
            return holdingMains;
        }

        let el =  holdingMains.filter(x => x.dzoId === dzoId)
        if ((el === null) || (el[0] === null) || (el[0] === undefined) ){
            return null;
        }

        return el[0].keyProjectDesc;
    },
}