package sample;

import xyz.sunnytoday.dto.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/test/login")
public class LoginTestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/test/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //테스트용이라 대충 했어요
        HttpSession session = req.getSession();
        if (session.getAttribute("member") != null) { //새로고침, 로그아웃 판별

            if (req.getParameter("logout") != null) {//로그아웃
                session.invalidate();
            }

            doGet(req, resp);
            return;
        }

        Member member = new Member();
        member.setUserno(Integer.parseInt(req.getParameter("userno")));
        member.setUserid(req.getParameter("userid").toString());
        member.setNick(req.getParameter("nick").toString());
        member.setAdmin(req.getParameter("admin").toString());
        try {
            member.setPictureno(Integer.parseInt(req.getParameter("pictureno")));
        } catch (NumberFormatException e) {
            member.setPictureno(0); //픽쳐넘버는 null이 가능한 값임. 자료형을 래퍼클래스인 Integer로 바꾸던 null일경우(프사가 없을경우) 어떤 숫자를 넣어줄건지 서로 합의필요 여기선 0으로 하겠음
        }


        session.setAttribute("member", member);
        doGet(req, resp);
        
        /*
        
        사용하시는 방법
        
        로그인여부 체크
        if(req.getSession().getAttribute("member") == null) {
            로그인 안돼있음
        }
        
        
        //로그인 사용자 데이터 가져오기
        Member member1 = (Member) req.getSession().getAttribute("member");
        member.getUserid().....  등 필요한 정보 꺼내서 사용
        
        
        현재로서 로그인시 들어있는 데이터
        userno
        userid
        nick
        admin
        picureno
        
        활용하기 힘들거나 비효율적인 데이터 빼고, 자주쓰는것들 추가하고 등은 아연님이랑 상의해서 하세요


         JSP에서 로그인여부 및 데이터 사용
         ${empty member}   // true가 나오면 비로그인
         ${not empty member}    //true가 나오면 로그인

         ${member.userno} 등으로 필요 데이터 꺼내쓰기

         !! 중요  아마 controller에서 jsp로 유저 데이터 내려줄때 req에 member라는 이름으로 담아서 내려주는경우가 많으실텐데
         그럴경우 ${member} 하면 req에 있는 member를 사용 하게 됩니다. 만약 세션에서 꺼내실거면 ${sessionScope.member} 로 꺼내시면 됩니다.

         */
    }
}
