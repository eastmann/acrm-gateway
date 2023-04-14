export default {
    creteColumnsProducts() {
        let columns = [
            {
                label: 'Продукт',
                field: 'productName',
                thClass: 'rt-header',
                tdClass: 'rt-col',
                // width: '200px',
            },
        ];

        return columns;
    },

    creteRows(products, dzoId) {
        if (products === null){
            return null;
        }
        return products.filter(x => x.dzoId === dzoId)
    },
}