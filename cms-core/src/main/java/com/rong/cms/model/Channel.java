package com.rong.cms.model;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_channel")
public class Channel {
    /**
     * 栏目主键
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 指定链接 0否 1是
     */
    private int customLink;
    /**
     * 指定链接url
     */
    private String customLinkUrl;
    /**
     *栏目类型，存在name属性，表示栏目名称
     */
    private ChannelType channelType;
    /**
     * 是否主页显示 0否 1是
     */
    private int isIndex;
    /**
     * 是否顶部导航 0否 1是
     */
    private int isTopNav;
    /**
     *是否推荐栏目 0否 1是
     */
    private int isRecommend;
    /**
     * 状态 0停用 1启用
     */
    private int status;
    /**
     * 栏目优先级
     */
    private int priority;
    /**
     * 栏目创建时间
     */
    private Date createDate;
    /**
     * 栏目修改时间
     */
    private Date updateDate;
    /**
     * 子栏目
     */
    private Channel parentChannel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "custom_link")
    public int getCustomLink() {
        return customLink;
    }

    public void setCustomLink(int customLink) {
        this.customLink = customLink;
    }
    @Column(name = "custom_link_url")
    public String getCustomLinkUrl() {
        return customLinkUrl;
    }

    public void setCustomLinkUrl(String customLinkUrl) {
        this.customLinkUrl = customLinkUrl;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name="channel_type")
    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }
    @Column(name = "is_index")
    public int getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(int isIndex) {
        this.isIndex = isIndex;
    }
    @Column(name = "is_top_nav")
    public int getIsTopNav() {
        return isTopNav;
    }

    public void setIsTopNav(int isTopNav) {
        this.isTopNav = isTopNav;
    }
    @Column(name = "is_recommend")
    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    @ManyToOne
    @JoinColumn(name = "pid")
    public Channel getParentChannel() {
        return parentChannel;
    }

    public void setParentChannel(Channel parentChannel) {
        this.parentChannel = parentChannel;
    }
}
