export const sendMetrik = (type, value) => {
    window.ym(87747728, type, value);
}

export const sendMetrikHit = (href) => {
    window.ym(87747728, 'hit', href, {
        title: document.title,
        referer: document.referrer
    });
}