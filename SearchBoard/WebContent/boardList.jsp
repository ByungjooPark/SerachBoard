<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.hk.*" %>
<%@ page import="java.util.*" %>

<%
	List<SBoardVO> list = (List<SBoardVO>)request.getAttribute("list");
	int totalPageCnt = (int)request.getAttribute("pageCnt");
%>


<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>등록날짜</th>
		<th>조회수</th>
	</tr>
	<%for(SBoardVO vo : list) { %>
		<tr>
			<td><%=vo.getBoard_no() %></td>
			<td><%=vo.getBoard_title() %></td>
			<td><%=vo.getRegdate() %></td>
			<td><%=vo.getCnt() %></td>
		</tr>
	<%
	}
	%>
</table>
<div>
	<%for(int i = 1; i <= totalPageCnt; i++) { %>
		<a href="list?searchWord=${searchWord }&searchType=${searchType}&page=<%=i%>"><%=i %></a>
	<%
	}
	%>
	
</div>
<div>
	<form action="list" method="get">
		<select name="searchType">
			<option value="0">전체</option>
			<option value="1">제목</option>
			<option value="2">내용</option>
		</select>
		<input type="text" name="searchWord">
		<input type="submit" value="검색">
	</form>
</div>