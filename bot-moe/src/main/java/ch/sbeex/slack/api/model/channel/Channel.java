/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package ch.sbeex.slack.api.model.channel;

/**
 * Channel
 * Represent a channel DTO of slack API
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */
public class Channel {

    private String id;
    private String name;
    private Boolean isChannel;
    private Long created;
    private String creator;
    private Boolean isArchived;
    private Boolean isGeneral;
    private Integer unlinked;
    private String nameNormalized;
    private Boolean isShared;
    private Boolean isOrgShared;
    private Boolean isPrivate;
    private Boolean isMpim;
    private String lastRead;
    private Latest latest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChannel() {
        return isChannel;
    }

    public void setChannel(Boolean channel) {
        isChannel = channel;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public Boolean getGeneral() {
        return isGeneral;
    }

    public void setGeneral(Boolean general) {
        isGeneral = general;
    }

    public Integer getUnlinked() {
        return unlinked;
    }

    public void setUnlinked(Integer unlinked) {
        this.unlinked = unlinked;
    }

    public Latest getLatest() {
        return latest;
    }

    public void setLatest(Latest latest) {
        this.latest = latest;
    }

    public String getNameNormalized() {
        return nameNormalized;
    }

    public void setNameNormalized(String nameNormalized) {
        this.nameNormalized = nameNormalized;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public Boolean getOrgShared() {
        return isOrgShared;
    }

    public void setOrgShared(Boolean orgShared) {
        isOrgShared = orgShared;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Boolean getMpim() {
        return isMpim;
    }

    public void setMpim(Boolean mpim) {
        isMpim = mpim;
    }

    public String getLastRead() {
        return lastRead;
    }

    public void setLastRead(String lastRead) {
        this.lastRead = lastRead;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isChannel=" + isChannel +
                ", created=" + created +
                ", creator='" + creator + '\'' +
                ", isArchived=" + isArchived +
                ", isGeneral=" + isGeneral +
                ", unlinked=" + unlinked +
                ", nameNormalized='" + nameNormalized + '\'' +
                ", isShared=" + isShared +
                ", isOrgShared=" + isOrgShared +
                ", isPrivate=" + isPrivate +
                ", isMpim=" + isMpim +
                ", lastRead='" + lastRead + '\'' +
                ", latest=" + latest +
                '}';
    }
}
