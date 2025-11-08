import request from '@/utils/request'

// 查询药品信息列表
export function listChstcmcatalog(query) {
  return request({
    url: '/system/chstcmcatalog/list',
    method: 'get',
    params: query
  })
}

// 查询药品信息详细
export function getChstcmcatalog(ID) {
  return request({
    url: '/system/chstcmcatalog/' + ID,
    method: 'get'
  })
}

// 新增药品信息
export function addChstcmcatalog(data) {
  return request({
    url: '/system/chstcmcatalog',
    method: 'post',
    data: data
  })
}

// 修改药品信息
export function updateChstcmcatalog(data) {
  return request({
    url: '/system/chstcmcatalog',
    method: 'put',
    data: data
  })
}

// 删除药品信息
export function delChstcmcatalog(ID) {
  return request({
    url: '/system/chstcmcatalog/' + ID,
    method: 'delete'
  })
}
