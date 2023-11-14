import request from '@/utils/request'

// 查询消息模版列表
export function listMessageTemplate(query) {
  return request({
    url: '/message/MessageTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询消息模版详细
export function getMessageTemplate(id) {
  return request({
    url: '/message/MessageTemplate/' + id,
    method: 'get'
  })
}

// 新增消息模版
export function addMessageTemplate(data) {
  return request({
    url: '/message/MessageTemplate',
    method: 'post',
    data: data
  })
}

// 修改消息模版
export function updateMessageTemplate(data) {
  return request({
    url: '/message/MessageTemplate',
    method: 'put',
    data: data
  })
}

// 删除消息模版
export function delMessageTemplate(id) {
  return request({
    url: '/message/MessageTemplate/' + id,
    method: 'delete'
  })
}
