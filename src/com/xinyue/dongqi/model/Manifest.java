package com.xinyue.dongqi.model;
/**
 * 货物清单  字段编号
 * @author chenhongyu
 *
 */
public class Manifest {
	private String col_0001;//客户名称
	private String col_0004;//订货日期
	private String col_0024;//门店地址
	private String col_0031;//客户单号
	private String col_0015;//编号
	private ManifestDetail manifestDetail;//单据详情
	public String getCol_0001() {
		return col_0001;
	}
	public void setCol_0001(String col_0001) {
		this.col_0001 = col_0001;
	}
	public String getCol_0004() {
		return col_0004;
	}
	public void setCol_0004(String col_0004) {
		this.col_0004 = col_0004;
	}
	public String getCol_0024() {
		return col_0024;
	}
	public void setCol_0024(String col_0024) {
		this.col_0024 = col_0024;
	}
	public String getCol_0031() {
		return col_0031;
	}
	public void setCol_0031(String col_0031) {
		this.col_0031 = col_0031;
	}
	public String getCol_0015() {
		return col_0015;
	}
	public void setCol_0015(String col_0015) {
		this.col_0015 = col_0015;
	}
	public ManifestDetail getManifestDetail() {
		return manifestDetail;
	}
	public void setManifestDetail(ManifestDetail manifestDetail) {
		this.manifestDetail = manifestDetail;
	}
	
	

}
