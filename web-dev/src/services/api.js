import axios from 'axios'

const apiClient = axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    // timeout: 10000, // 10000ms, ie. 10 seconds
    headers: {
        'Content-Type': 'application/json'
    }
})

export default apiClient
