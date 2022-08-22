import axios from 'axios'
const service = axios.create({
    baseURL: process.env.BASE_API, // api的base_url
    timeout: 5000 // 请求超时时间
  })
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'


// 响应拦截器
service.interceptors.response.use(response => {
    return response.data;
  },
  error => {
  // 失败处理
   switch (error.response.status) {
     case 400:
       error.message = '错误请求';
       break;
     case 401:
       error.message = '未授权，请重新登录: ';
       break;
     case 403:
       error.message = '拒绝访问';
       break;
     case 404:
       error.message = `请求出错: ${error.response.config.url}`;
       break;
     case 405:
       error.message = `请求方法未允许: ${error.response.config.method}`;
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
       error.message = '网络未实现！';
       break;
     case 502:
       error.message = '网络错误！';
       break;
     case 503:
       error.message = '服务不可用！';
       break;
     case 504:
       error.message = '网络超时';
       break;
     case 505:
       error.message = 'HTTP 版本不受支持！';
       break;
     default:
       error.message = '异常问题，请联系管理员！';
       break;
   }
   return Promise.reject(error);
  });
 service.interceptors.request.use(config => {
  // 发送请求，开始进度条
  return config
});

 export default service;

