export default {
    creteRows(products, dzoId) {
        if (products === null) {
            return null;
        }
        return products.filter(x => x.dzoId === dzoId)
    },
}