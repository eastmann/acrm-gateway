import apiClient from './api';

function cloneObject(src) {
    return Object.assign({}, src);
}

let baseHeaders = {};

let notification = function (type, message) {
    alert(type, message)
    //console.error('Unhandled ', type, ': ', message);
}

// result wrapper

let onSuccess = function (callback) {
    return function(data) {
        if (callback) {
            callback(data.data);
        }
    }
};

let onError = function (errorCallback) {
    let defaultErrorHandler = function(data) {
        if (data.code) {
            notification('warning', data.message, 5000);
        } else if (data.errors) {
            //default spring error handling
            notification('warning', data.errors[0]["default_message"], 5000);
        } else {
            notification('error', 'unknow error', 5000);
        }
    };
    return function (data) {
        data = data.response.data;
        if (!errorCallback) {
            defaultErrorHandler(data);
            return;
        }
        errorCallback(data, function () {defaultErrorHandler(data)});
    }
};

let wrapPromise = function (promise) {
    return new Promise((resolve, reject) => {
        promise
            .then(onSuccess(resolve))
            .catch(onError(reject));
    });
};

let getHeaders = function (contentType) {
    let headers = cloneObject(baseHeaders);
        if (contentType) headers['Content-type'] = contentType;
        return headers;
};

class ApiService {
    get(url) {
        return wrapPromise(apiClient.get(url));
    }

    postJson(url, data) {
        let option = {headers: getHeaders('application/json')};
        return wrapPromise(apiClient.post(url, data, option));
    }

    postMultipart(url, formData) {
        let option = {headers: getHeaders('multipart/form-data')};
        return wrapPromise(apiClient.post(url, formData, option));
    }

    getFile(url) {
        let option = {
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                mode: 'no-cors'
            }}
        return wrapPromise(apiClient.get(url, option));
    }
}

export const apiService = new ApiService()
