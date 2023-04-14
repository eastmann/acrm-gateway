export default {
    creteColumnsReports() {
        let columns = [
            {
                label: 'Название отчета',
                field: 'name',
                thClass: 'rt-header',
                tdClass: 'rt-col',
            },
            {
                label: '',
                field: 'size',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '100px',
            },
            {
                label: '',
                field: 'link',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '120px',
            },
            {
                label: '',
                field: 'format',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                width: '120px',
                hidden: true,
            },
        ];

        return columns;
    },

    creteRowsReports() {
        let rows =  [
            {
                "id": 1,
                "name": 'Справка по клиенту',
                "size": '24 Кб',
                "format": 'DOC',
                "link": 'link1',
            },
            // {
            //     "id": 2,
            //     "name": 'Справка по клиенту',
            //     "size": '24 Кб',
            //     "format": 'DOC',
            //     "link": "link2",
            // },
            // {
            //     "id": 3,
            //     "name": 'Справка по клиенту',
            //     "size": '24 Кб',
            //     "format": 'DOC',
            //     "link": "link1",
            // },
            // {
            //     "id": 4,
            //     "name": 'Справка по клиенту',
            //     "size": '24 Кб',
            //     "format": 'DOC',
            //     "link": "link1",
            // },
            // {
            //     "id": 5,
            //     "name": 'Справка по клиенту',
            //     "size": '24 Кб',
            //     "format": 'DOC',
            //     "link": "link1",
            // },
        ];

        return rows;
    },
}