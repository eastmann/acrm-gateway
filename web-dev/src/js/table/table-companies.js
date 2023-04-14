export default {
    creteHeadersVir() {
        let headers = [
                {
                    text: "",
                    value: "name",
                    width: "32%",
                    filterable: true,
                    hidden: false,
                    subHeaders: [
                        {
                            text: "Наименование ГК/Компании",
                            value: "nameOrg",
                            width: '32%',
                            filterable: true,
                            hidden: false
                        },
                    ]
                },
                {
                    value: "vyruchkaRtk",
                    text: "Выручка РТК, млн. ₽",
                    filterable: true,
                    hidden: false,
                    subHeaders: [
                        {
                            text: "2022",
                            value: "vyruchkaRtk2022",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                        {
                            text: "2023",
                            value: "vyruchkaRtk2023",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                    ]
                },
                {
                    value: "vyruchkaTsod",
                    text: "Выручка ЦОД, млн. ₽",
                    filterable: true,
                    hidden: false,
                    subHeaders: [
                        {
                            text: "2022",
                            value: "vyruchkaTsod2022",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                        {
                            text: "2023",
                            value: "vyruchkaTsod2023",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                    ]
                },
                {
                    value: "vyruchkaSolar",
                    text: "Выручка Солар, млн. ₽",
                    filterable: true,
                    hidden: false,
                    subHeaders: [
                        {
                            text: "2022",
                            value: "vyruchkaSolar2022",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                        {
                            text: "2023",
                            value: "vyruchkaSolar2023",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                    ]
                },
                {
                    value: "vyruchkaTele2",
                    text: "Выручка Теле2, млн. ₽",
                    filterable: true,
                    hidden: false,
                    subHeaders: [
                        {
                            text: "2022",
                            value: "vyruchkaTele22022",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                        {
                            text: "2023",
                            value: "vyruchkaTele22023",
                            width: '8,5%',
                            filterable: true,
                            hidden: false,
                            sortable: true,
                        },
                    ]
                },
            ];

        return headers;
    },

    creteHeadersKam() {
        let headers = [
                {
                    text: "Наименование ГК/Компании",
                    value: "nameOrg",
                    width: "32%",
                    filterable: true,
                    hidden: false,
                    sortable: true,
                },
                {
                    text: "КАМ РТК",
                    value: "kamRtk",
                    sortable: true,
                    width: "17%",
                    filterable: true,
                    hidden: false
                },
                {
                    text: "КАМ ЦОД",
                    value: "kamTsod",
                    sortable: true,
                    width: "17%",
                    filterable: true,
                    hidden: false
                },
                {
                    text: "КАМ Солар",
                    value: "kamSolar",
                    sortable: true,
                    width: "17%",
                    filterable: true,
                    hidden: false
                },
                {
                    text: "КАМ Теле2",
                    value: "kamTele2",
                    sortable: true,
                    width: "17%",
                    filterable: true,
                    hidden: false
                },
                {
                    value: "isHolding",
                    text: "Холдинг",
                    sortable: true,
                    hidden: true
                }
            ];

            return headers;
    },

    creteRows(products) {
        if (products === null) {
            return null;
        }
        return products;
    },
}