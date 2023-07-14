//package com.stc.assessments.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import com.stc.assessments.model.PermissionGroups;
//import com.stc.assessments.model.Permissions;
//import com.stc.assessments.repository.PermissionGroupsRepository;
//import com.stc.assessments.repository.PermissionsRepository;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//public class RoleRepositoryTests {
//	@Autowired
//	private PermissionsRepository repoUser;
//	@Autowired
//	private PermissionGroupsRepository repoRole;
//	/// run this first to set permissiongroups role
//
////    @Test
////    public void testCreateRoles() {
////    	PermissionGroups admin = new PermissionGroups("ROLE_ADMIN");
////    	PermissionGroups user = new PermissionGroups("ROLE_USER");
////       
////         
////        repoRole.saveAllAndFlush(List.of(admin, user));
////         
////        long count = repoRole.count();
////        assertEquals(2, count);
////    }
//
//	// run this to assign permissionGroups to permissions
////    @Test
////    public void testAssignRoleToUser() {
////    	Integer userId1 = (Integer) 5;
////        Integer roleId1 = 1;
////        
////        Integer userId2 = (Integer) 6;
////        Integer roleId2 = 2;
////        Integer userId3 = (Integer) 7;
////        
////
////        //Integer roleId3 = 3;
////       Permissions user1 = repoUser.findById(userId1).get();
////       Permissions user2 = repoUser.findById(userId2).get();
////       Permissions user3 = repoUser.findById(userId3).get();
////      
////       user1.addPermissionGroup(new PermissionGroups(roleId1));
////        user2.addPermissionGroup(new PermissionGroups(roleId2));
////       
////       user3.addPermissionGroup(new PermissionGroups(roleId1));
////        repoUser.saveAllAndFlush(List.of(user1,user2,user3));
////        long count = repoUser.count();
////        assertEquals(3, count);
////        
////         
////    }
//}