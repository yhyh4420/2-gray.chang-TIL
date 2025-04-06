package org.til.proxy;

public class ServiceProxy implements Service {
    private final ServiceImpl serviceImpl;
    private final String userRole;

    public ServiceProxy(ServiceImpl serviceImpl, String userRole) {
        this.serviceImpl = serviceImpl;
        this.userRole = userRole;
    }

    @Override
    public void action() {
        System.out.println("[프록시] 사용자 권한 확인 중");
        if (!userRole.equals("admin")) {
            System.out.println("[프록시] 사용자 권한 부족, 관리자가 아닙니다. 현재 유저 권한 : " + userRole);
            return;
        }
        System.out.println("[프록시] 사용자 권한 확인 완료! 현재 유저 권한 : " + userRole);
        serviceImpl.action();
    }
}
