export default {
    creteColumnsCamDir(name) {
        let columns = [
            {
                text: 'ФИО',
                value: 'kam_name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
            {
                text: 'Телефон ' + name,
                value: 'kam_phone',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
            {
                text: 'Email ' + name,
                value: 'kam_email',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
            {
                text: 'Тип',
                value: 'type_name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
        ];

        return columns;
    },

    creteColumnsLpr(name) {
        let columns = [
            {
                text: 'ФИО ' + name,
                value: 'kam_name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
            {
                text: 'Телефон ' + name,
                value: 'kam_phone',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
            {
                text: 'Email' + name,
                value: 'kam_email',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
            {
                text: 'Тип',
                value: 'type_name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '25%',
            },
        ];

        return columns;
    },

    creteColumnsDir(name) {
        let columns = [
            {
                text: 'Директор Холдинга',
                value: 'dirH',
                thClass: 'rt-header',
                tdClass: 'rt-col',
            },
            {
                text: 'Контакт директора Холдинга',
                value: 'dirHContact',
                thClass: 'rt-header',
                tdClass: 'rt-col',
            },
            {
                text: 'Директор ' + name,
                value: 'dirO',
                thClass: 'rt-header',
                tdClass: 'rt-col',
            },
            {
                text: 'Контакт директора ' + name,
                value: 'dirOContact',
                thClass: 'rt-header',
                tdClass: 'rt-col',
            },
        ];

        return columns;
    },

    creteColumnsHolding() {
        let columns = [
            {
                text: 'ФИО',
                value: 'kam_name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '70%'
            },
            {
                text: 'Тип',
                value: 'type_name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '30%'
            },
        ];

        return columns;
    },

    creteRowsCam(kamLprInfo, dzoId) {
        if (kamLprInfo === null){
            return null;
        }
        return kamLprInfo.filter(x => (x.dzo_id === dzoId) && ((x.kam_type_id >= 1) && (x.kam_type_id <= 3)))
    },

    creteRowsLpr(kamLprInfo, dzoId) {
        if (kamLprInfo === null){
            return null;
        }
        return kamLprInfo.filter(x => (x.dzo_id === dzoId) && (x.kam_type_id === 4))
    },

    creteRowsDir(kamLprInfo, dzoId) {
        if (kamLprInfo === null){
            return null;
        }
        return kamLprInfo.filter(x => (x.dzo_id === dzoId) && (x.kam_type_id === 5))
    },
}