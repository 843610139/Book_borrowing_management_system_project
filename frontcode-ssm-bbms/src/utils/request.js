import axios from 'axios'
const service = axios.create({
    baseURL: process.env.BASE_API, // api的base_url
    timeout: 5000 // 请求超时时间
  })


// 响应拦截器
service.interceptors.response.use(response => {
    return response.data;
  },

  error => {
  // 失败处理
   switch (error.response.status) {
     case 302:
       error.message = '接口重定向了！';
       break;
     case 400:
       error.message = '参数不正确！';
       break;
     case 401:
       error.message = '您未登录，或者登录已经超时，请先登录！';
       break;
     case 403:
       error.message = '您没有权限操作！';
       break;
     case 404:
       error.message = `请求地址出错: ${error.response.config.url}`;
       break;
     case 408:
       error.message = '请求超时！';
       break;
     case 409:
       error.message = '系统已存在相同数据！';
       break;
     case 500:
       error.message = '服务器内部错误！';
       break;
     case 501:
       error.message = '服务未实现！';
       break;
     case 502:
       error.message = '网关错误！';
       break;
     case 503:
       error.message = '服务不可用！';
       break;
     case 504:
       error.message = '服务暂时无法访问，请稍后再试！';
       break;
     case 505:
       error.message = 'HTTP 版本不受支持！';
       break;
     default:
       error.message = '异常问题，请联系管理员！';
       break;
   }
   return Promise.reject(error);
  }
);
 service.interceptors.request.use(config => {
  // 发送请求，开始进度条
  return config
});
 export default service;

