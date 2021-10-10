package xyz.sunnytoday.util;

public class Paging {
	private int curPage; // 현재 페이지 번호
	
	private int totalCount; // 총 게시글 수
	private int listCount; // 페이지당 보여질 리스트 수
	private int totalPage; //총 페이지 수
	
	private int pageCount; // 한 화면에 보여질 페이지 수
	private int startPage; // 화면에 보이는 시작 페이지네이션 번호
	private int endPage; // 화면에 보이는 끝 페이지네이션 번호
	
	private int startno; // 화면에 보이는 게시글 시작 번호
	private int endno; // 화면에 보이는 게시글 끝 번호
	
	//디폴트 생성자 - 페이징 계산X
	public Paging() {}
	
	//총 게시글 수 현재 페이지 번호를 입력하는 생성자
	public Paging(int totalCount, int curPage) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		makePaging();
	}
	
	//총 게시글 수 현재 페이지 번호, 보여질 게시글 수를 입력하는 생성자
	public Paging(int totalCount, int curPage, int listCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		makePaging();
	}
	
	//총 게시글 수 현재 페이지 번호, 보여질 게시글 수, 보여질 페이지 수를 입력하는 생성자
	public Paging(int totalCount, int curPage, int listCount, int pageCount) {
		setTotalCount(totalCount);
		setCurPage(curPage);
		setListCount(listCount);
		setPageCount(pageCount);
		makePaging();
	}
	
	private void makePaging() {
		if(totalCount == 0) return; //게시글X
		
		//기본값 설정
		if(curPage == 0) setCurPage(1); //첫페이지 설정
		if(listCount == 0) setListCount(10); // 화면에 게시글이  기본으로10개씩 보여짐
		if(pageCount == 0) setPageCount(10); //화면에 페이지가 기본으로 10개씩 보여짐
		
		//총페이지 수 계산
		totalPage = totalCount / listCount;
		if(totalCount % listCount > 0) totalCount++;
		
		//현재 페이지값 보정
		if(curPage > totalPage) curPage = totalPage;
		
		//화면에 보여질 페이지네이션의 시작번호와 끝번호 
		startPage =((curPage-1)/pageCount) * pageCount + 1;
		endPage = startPage + pageCount - 1;
		
		//페이지네이션 보정
		if(endPage > totalPage) endPage = totalPage;
		
		//화면에 보이는 게시글의 시작 / 끝 번호
		startno = (curPage - 1) * listCount + 1;
		endno = curPage * listCount;
	}
	
	@Override
	public String toString() {
		return "Paging [curPage=" + curPage + ", totalCount=" + totalCount + ", listCount=" + listCount + ", totalPage="
				+ totalPage + ", pagecount=" + pageCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startno=" + startno + ", endno=" + endno + "]";
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pagecount) {
		this.pageCount = pagecount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartno() {
		return startno;
	}
	public void setStartno(int startno) {
		this.startno = startno;
	}
	public int getEndno() {
		return endno;
	}
	public void setEndno(int endno) {
		this.endno = endno;
	}
	
}
