import {apiService} from '../services/api-service'
import {getErrorObject} from '../utils/errors'
import urls from "../js/utils/urls";

class ProfileApiService {

    getClient() {
        return new Promise((resolve, reject) => {
            let url = urls.getUser()
            apiService.get(url)
                .then(data => {
                    resolve(data)
                })
                .catch(e => {
                    reject(getErrorObject(e))
                })
        })
    }

}

export const profileApiService = new ProfileApiService();