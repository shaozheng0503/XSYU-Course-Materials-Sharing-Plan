/* 
专门用于存储用户状态信息的pinia

*/
import {defineStore} from 'pinia'

export const defineUser= defineStore(
    "loginUser",
    {
        state:()=>{
            return {
               uid:0,
               username:'' 
            }
        },
        getters:{

        },
        actions:{

        }
    }
)
