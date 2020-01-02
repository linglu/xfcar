package com.xfcar.driver.model.bean;

public class IntegralGoodsEntity {
	public int id; // 主键ID
	public String addTime; // 添加时间
	public String beginTime; // 兑换开始时间
	public String endTime; // 兑换结束时间
	public String content; // 礼品详情
	public int exchangeCount; // 礼品兑出数量
	public int goodsCount; // 礼品库存数量
	public float goodsIntegral; // 礼品兑换积分
	public String goodsName; // 礼品名称
	public float goodsPrice; // 礼品原价
	public String goodsSn; // 礼品编号
	public int isvirtual; // 是否虚拟物品 0 非  1 是
	public String goodsTag; // 礼品标签
	public int limitCount; // 限制兑换数量
	public boolean limitType; // 是否限制用户兑换数量
	public boolean recommend; // 是否推荐
	public boolean putaway; // 是否上架
	public boolean timeType; // 是否限制兑换时间
	public float transfee; // 运费
	public int transfeeType; // 运费承担方式，0为卖家承担，1为买家承担
	public int userLevel; // 礼品兑换所需的用户等级，0—铜牌1—银牌2—金牌3—超级
	public int goodsImgId; // 礼品图片
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String updateDate; // 修改时间
	public int updateBy; // 修改人
}
