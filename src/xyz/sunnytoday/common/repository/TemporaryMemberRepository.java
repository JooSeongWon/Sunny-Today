package xyz.sunnytoday.common.repository;

import xyz.sunnytoday.common.config.AppConfig;
import xyz.sunnytoday.common.task.CustomTaskTimer;
import xyz.sunnytoday.dto.Member;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class TemporaryMemberRepository {
    private final Map<String, Member> temporaryMembers = new HashMap<>();
    private final Set<String> idSet = new HashSet<>();
    private final Set<String> nickSet = new HashSet<>();
    private final Set<String> emailSet = new HashSet<>();


    synchronized public boolean isIdDuplicated(String id) {
        return idSet.contains(id);
    }

    synchronized public boolean isNickDuplicated(String nick) {
        return nickSet.contains(nick);
    }

    synchronized public boolean isEmailDuplicated(String email) {
        return emailSet.contains(email);
    }

    public Member getMember(String secretCode) {
        String uuid = new String(Base64.getDecoder().decode(secretCode), StandardCharsets.UTF_8);
        if (!temporaryMembers.containsKey(uuid)) {
            return null; //이미 유효하지 않은 코드
        }
        return temporaryMembers.get(uuid);
    }


    public void deleteMember(String secretCode) {
        String uuid = new String(Base64.getDecoder().decode(secretCode), StandardCharsets.UTF_8);

        final Member member = temporaryMembers.get(uuid);
        if (member == null) { //이미 삭제된 데이터
            return;
        }

        System.out.println("우와우 30분걸리는 지우개 테스트!");
        System.out.println("member = " + member);

        idSet.remove(member.getUserid());
        nickSet.remove(member.getNick());
        emailSet.remove(member.getEmail());
        temporaryMembers.remove(uuid);

        System.out.println("temporaryMembers.containsKey(uuid) = " + temporaryMembers.containsKey(uuid));
    }

    synchronized public String addMember(Member member) throws IllegalArgumentException {
        //중복확인
        if (idSet.contains(member.getUserid()) ||
                nickSet.contains(member.getNick()) ||
                emailSet.contains(member.getEmail())
        ) {
            throw new IllegalArgumentException("중복된 데이터가 존재합니다.");
        }

        //시크릿 코드 생성
        String uuid = UUID.randomUUID().toString();
        while (temporaryMembers.containsKey(uuid)) {
            uuid = UUID.randomUUID().toString();
        }

        String secretCode = Base64.getEncoder().encodeToString(uuid.getBytes(StandardCharsets.UTF_8));

        //저장
        temporaryMembers.put(uuid, member);
        idSet.add(member.getUserid());
        nickSet.add(member.getNick());
        emailSet.add(member.getEmail());

        //30~35분후 제거
        AppConfig.addTask(new CustomTaskTimer(35, () -> AppConfig.getTemporaryMemberRepo().deleteMember(secretCode), false));

        return secretCode;
    }


}
