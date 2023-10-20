package study.kimdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.kimdatajpa.dto.MemberDto;
import study.kimdatajpa.entity.Member;
import study.kimdatajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    // 도메인 클래스 컨버터 사용
    // HTTP 파라미터로 넘어온 엔티티의 아이디로 엔티티 객체를 찾아서 바인딩
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    // 페이징과 정렬
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5, sort = "username", // 글로벌 설정보다 우선함
        direction = Sort.Direction.DESC) Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDto::new); // Page 내용을 DTO 로 변환
    }

    //@PostConstruct
    public void init() {
        //memberRepository.save(new Member("userA")); // 도메인 클래스 컨버터 테스트
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i)); // 페이징 테스트
        }
    }
}