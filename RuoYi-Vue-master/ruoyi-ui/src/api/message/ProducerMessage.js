import request from '@/utils/request'

// 查询发送方消息列表
export function listProducerMessage(query) {
  return request({
    url: '/message/ProducerMessage/list',
    method: 'get',
    params: query
  })
}

// 查询发送方消息详细
export function getProducerMessage(id) {
  return request({
    url: '/message/ProducerMessage/' + id,
    method: 'get'
  })
}

// 新增发送方消息
export function addProducerMessage(data) {
  return request({
    url: '/message/ProducerMessage',
    method: 'post',
    data: data
  })
}

// 修改发送方消息
export function updateProducerMessage(data) {
  return request({
    url: '/message/ProducerMessage',
    method: 'put',
    data: data
  })
}

// 删除发送方消息
export function delProducerMessage(id) {
  return request({
    url: '/message/ProducerMessage/' + id,
    method: 'delete'
  })
}
