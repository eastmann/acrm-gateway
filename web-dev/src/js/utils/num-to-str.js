export default {

    /**
     * Возвращает сумму прописью
     */
    number_to_string(num) {
        var def_translite = {
            null: 'ноль',
            a1: ['один', 'два', 'три', 'четыре', 'пять', 'шесть', 'семь', 'восемь', 'девять'],
            a2: ['одна', 'две', 'три', 'четыре', 'пять', 'шесть', 'семь', 'восемь', 'девять'],
            a10: ['десять', 'одиннадцать', 'двенадцать', 'тринадцать', 'четырнадцать', 'пятнадцать', 'шестнадцать', 'семнадцать', 'восемнадцать', 'девятнадцать'],
            a20: ['двадцать', 'тридцать', 'сорок', 'пятьдесят', 'шестьдесят', 'семьдесят', 'восемьдесят', 'девяносто'],
            a100: ['сто', 'двести', 'триста', 'четыреста', 'пятьсот', 'шестьсот', 'семьсот', 'восемьсот', 'девятьсот'],
            uc: ['копейка', 'копейки', 'копеек'],
            ur: ['рубль', 'рубля', 'рублей'],
            u3: ['тысяча', 'тысячи', 'тысяч'],
            u2: ['миллион', 'миллиона', 'миллионов'],
            u1: ['миллиард', 'миллиарда', 'миллиардов'],
        }
        var i1, i2, i3, kop, rub, v, _ref, _ref1, _ref2, ax;

        _ref = parseFloat(num).toFixed(2).split('.'), rub = _ref[0], kop = _ref[1];
        var leading_zeros = 12 - rub.length;
        if (leading_zeros < 0) {
            return false;
        }

        var zeros = [];
        while (leading_zeros--) {
            zeros.push('0');
        }
        rub = zeros.join('') + rub;
        var out = [];
        if (rub > 0) {
            // Разбиваем число по три символа
            _ref1 = this.str_split(rub, 3);
            for (var i = -1; i < _ref1.length; i++) {
                v = _ref1[i];
                if (!(v > 0)) continue;
                _ref2 = this.str_split(v, 1), i1 = parseInt(_ref2[0]), i2 = parseInt(_ref2[1]), i3 = parseInt(_ref2[2]);
                out.push(def_translite.a100[i1 - 1]); // 1xx-9xx
                ax = (i + 1 == 3) ? 'a2' : 'a1';
                if (i2 > 1) {
                    out.push(def_translite.a20[i2 - 2] + (i3 > 0 ? ' ' + def_translite[ax][i3 - 1] : '')); // 20-99
                } else {
                    out.push(i2 > 0 ? def_translite.a10[i3] : def_translite[ax][i3 - 1]); // 10-19 | 1-9
                }

                if (_ref1.length > i + 1) {
                    var name = def_translite['u' + (i + 1)];
                    out.push(this.morph(v, name));
                }
            }
        } else {
            out.push(def_translite.null);
        }
        // Дописываем название "рубли"
        out.push(this.morph(rub, def_translite.ur));
        // Дописываем название "копейка"
        out.push(kop + ' ' + this.morph(kop, def_translite.uc));

        // Объединяем маcсив в строку, удаляем лишние пробелы и возвращаем результат
        let result = out.join(' ').replace(RegExp(' {2,}', 'g'), ' ').trimLeft();
        return this.ucFirst(result);
    },

    ucFirst(str) {
        if (!str) return str;
        return str[0].toUpperCase() + str.slice(1);
    },


    /**
     * Склоняем словоформу
     */
    morph(number, titles) {
        var cases = [2, 0, 1, 1, 1, 2];
        return titles[(number % 100 > 4 && number % 100 < 20) ? 2 : cases[Math.min(number % 10, 5)]];
    },

    /**
     * Преобразует строку в массив
     */
    str_split(string, length) {

        string = (string == null) ? "" : string;
        length = (length == null) ? 1 : length;

        var chunks = [];
        var pos = 0;
        var len = string.length;
        while (pos < len) {
            chunks.push(string.slice(pos, pos += length));
        }

        return chunks;
    }

}