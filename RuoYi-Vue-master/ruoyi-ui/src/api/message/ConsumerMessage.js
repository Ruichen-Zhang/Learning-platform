import request from '@/utils/request'

// 查询接收方消息列表
export function listConsumerMessage(query) {
  return request({
    url: '/message/ConsumerMessage/list',
    method: 'get',
    params: query
  })
}

// 查询接收方消息详细
export function getConsumerMessage(id) {
  return request({
    url: '/message/ConsumerMessage/' + id,
    method: 'get'
  })
}

// 新增接收方消息
export function addConsumerMessage(data) {
  return request({
    url: '/message/ConsumerMessage',
    method: 'post',
    data: data
  })
}

// 修改接收方消息
export function updateConsumerMessage(data) {
  return request({
    url: '/message/ConsumerMessage',
    method: 'put',
    data: data
  })
}

// 删除接收方消息
export function delConsumerMessage(id) {
  return request({
    url: '/message/ConsumerMessage/' + id,
    method: 'delete'
  })
}
