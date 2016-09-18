/*
 * Charlatano is a premium CS:GO cheat ran on the JVM.
 * Copyright (C) 2016 - Thomas Nappo, Jonathan Beaudoin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.charlatano.game.entity

import com.charlatano.game.CSGO.csgoEXE
import org.jire.arrowhead.unsign

enum class EntityType(val id: Long, val weapon: Boolean = false, val grenade: Boolean = false) {

	NULL(-1),
	NextBotCombatCharacter(0),
	CWeaponCubemap(0),
	CPropVehicleChoreoGeneric(0),
	CAI_BaseNPC(0),
	CAK47(1, weapon = true),
	CBaseAnimating(2),
	CBaseAnimatingOverlay(3),
	CBaseAttributableItem(4),
	CBaseButton(5),
	CBaseCombatCharacter(6),
	CBaseCombatWeapon(7),
	CBaseCSGrenade(8, grenade = true),
	CBaseCSGrenadeProjectile(9, grenade = true),
	CBaseDoor(10),
	CBaseEntity(11),
	CBaseFlex(12),
	CBaseGrenade(13, grenade = true),
	CBaseParticleEntity(14),
	CBasePlayer(15),
	CBasePropDoor(16),
	CBaseTeamObjectiveResource(17),
	CBaseTempEntity(18),
	CBaseToggle(19),
	CBaseTrigger(20),
	CBaseViewModel(21),
	CBaseVPhysicsTrigger(22),
	CBaseWeaponWorldModel(23),
	CBeam(24),
	CBeamSpotlight(25),
	CBoneFollower(26),
	CBreakableProp(27),
	CBreakableSurface(28),
	CC4(29),
	CCascadeLight(30),
	CChicken(31),
	CColorCorrection(32),
	CColorCorrectionVolume(33),
	CCSGameRulesProxy(34),
	CCSPlayer(35),
	CCSPlayerResource(36),
	CCSRagdoll(37),
	CCSTeam(38),
	CDEagle(39, weapon = true),
	CDecoyGrenade(40, grenade = true),
	CDecoyProjectile(41, grenade = true),
	CDynamicLight(42),
	CDynamicProp(43),
	CEconEntity(44),
	CEmbers(45),
	CEntityDissolve(46),
	CEntityFlame(47),
	CEntityFreezing(48),
	CEntityParticleTrail(49),
	CEnvAmbientLight(50),
	CEnvDetailController(51),
	CEnvDOFController(52),
	CEnvParticleScript(53),
	CEnvProjectedTexture(54),
	CEnvQuadraticBeam(55),
	CEnvScreenEffect(56),
	CEnvScreenOverlay(57),
	CEnvTonemapController(58),
	CEnvWind(59),
	CFireCrackerBlast(60),
	CFireSmoke(61),
	CFireTrail(62),
	CFish(63),
	CFlashbang(64, grenade = true),
	CFogController(65),
	CFootstepControl(66),
	CFunc_Dust(67),
	CFunc_LOD(68),
	CFuncAreaPortalWindow(69),
	CFuncBrush(70),
	CFuncConveyor(71),
	CFuncLadder(72),
	CFuncMonitor(73),
	CFuncMoveLinear(74),
	CFuncOccluder(75),
	CFuncReflectiveGlass(76),
	CFuncRotating(77),
	CFuncSmokeVolume(78),
	CFuncTrackTrain(79),
	CGameRulesProxy(80),
	CHandleTest(81),
	CHEGrenade(82, grenade = true),
	CHostage(83),
	CHostageCarriableProp(84),
	CIncendiaryGrenade(85, grenade = true),
	CInferno(86, grenade = true),
	CInfoLadderDismount(87),
	CInfoOverlayAccessor(88),
	CItem_Healthshot(89),
	CKnife(90, weapon = true),
	CKnifeGG(91, weapon = true),
	CLightGlow(92),
	CMaterialModifyControl(93),
	CMolotovGrenade(94, grenade = true),
	CMolotovProjectile(95, grenade = true),
	CMovieDisplay(96),
	CParticleFire(97),
	CParticlePerformanceMonitor(98),
	CParticleSystem(99),
	CPhysBox(100),
	CPhysBoxMultiplayer(101),
	CPhysicsProp(102),
	CPhysicsPropMultiplayer(103),
	CPhysMagnet(104),
	CPlantedC4(105),
	CPlasma(106),
	CPlayerResource(107),
	CPointCamera(108),
	CPointCommentaryNode(109),
	CPoseController(110),
	CPostProcessController(111),
	CPrecipitation(112),
	CPrecipitationBlocker(113),
	CPredictedViewModel(114),
	CProp_Hallucination(115),
	CPropDoorRotating(116),
	CPropJeep(117),
	CPropVehicleDriveable(118),
	CRagdollManager(119),
	CRagdollProp(120),
	CRagdollPropAttached(121),
	CRopeKeyframe(122),
	CSCAR17(123, weapon = true),
	CSceneEntity(124),
	CSensorGrenade(125),
	CSensorGrenadeProjectile(126),
	CShadowControl(127),
	CSlideshowDisplay(128),
	CSmokeGrenade(129, grenade = true),
	CSmokeGrenadeProjectile(130, grenade = true),
	CSmokeStack(131),
	CSpatialEntity(132),
	CSpotlightEnd(133),
	CSprite(134),
	CSpriteOriented(135),
	CSpriteTrail(136),
	CStatueProp(137),
	CSteamJet(138),
	CSun(139),
	CSunlightShadowControl(140),
	CTeam(141),
	CTeamplayRoundBasedRulesProxy(142),
	CTEArmorRicochet(143),
	CTEBaseBeam(144),
	CTEBeamEntPoint(145),
	CTEBeamEnts(146),
	CTEBeamFollow(147),
	CTEBeamLaser(148),
	CTEBeamPoints(149),
	CTEBeamRing(150),
	CTEBeamRingPoint(151),
	CTEBeamSpline(152),
	CTEBloodSprite(153),
	CTEBloodStream(154),
	CTEBreakModel(155),
	CTEBSPDecal(156),
	CTEBubbles(157),
	CTEBubbleTrail(158),
	CTEClientProjectile(159),
	CTEDecal(160),
	CTEDust(161),
	CTEDynamicLight(162),
	CTEEffectDispatch(163),
	CTEEnergySplash(164),
	CTEExplosion(165),
	CTEFireBullets(166),
	CTEFizz(167),
	CTEFootprintDecal(168),
	CTEFoundryHelpers(169),
	CTEGaussExplosion(170),
	CTEGlowSprite(171),
	CTEImpact(172),
	CTEKillPlayerAttachments(173),
	CTELargeFunnel(174),
	CTEMetalSparks(175),
	CTEMuzzleFlash(176),
	CTEParticleSystem(177),
	CTEPhysicsProp(178),
	CTEPlantBomb(179),
	CTEPlayerAnimEvent(180),
	CTEPlayerDecal(181),
	CTEProjectedDecal(182),
	CTERadioIcon(183),
	CTEShatterSurface(184),
	CTEShowLine(185),
	CTesla(186),
	CTESmoke(187),
	CTESparks(188),
	CTESprite(189),
	CTESpriteSpray(190),
	CTest_ProxyToggle_Networkable(191),
	CTestTraceline(192),
	CTEWorldDecal(193),
	CTriggerPlayerMovement(194),
	CTriggerSoundOperator(195),
	CVGuiScreen(196),
	CVoteController(197),
	CWaterBullet(198),
	CWaterLODControl(199),
	CWeaponAug(200, weapon = true),
	CWeaponAWP(201, weapon = true),
	CWeaponBaseItem(202),
	CWeaponBizon(203, weapon = true),
	CWeaponCSBase(204, weapon = true),
	CWeaponCSBaseGun(205, weapon = true),
	CWeaponCycler(206, weapon = true),
	CWeaponElite(207, weapon = true),
	CWeaponFamas(208, weapon = true),
	CWeaponFiveSeven(209, weapon = true),
	CWeaponG3SG1(210, weapon = true),
	CWeaponGalil(211, weapon = true),
	CWeaponGalilAR(212, weapon = true),
	CWeaponGlock(213, weapon = true),
	CWeaponHKP2000(214, weapon = true),
	CWeaponM249(215, weapon = true),
	CWeaponM3(216, weapon = true),
	CWeaponM4A1(217, weapon = true),
	CWeaponMAC10(218, weapon = true),
	CWeaponMag7(219, weapon = true),
	CWeaponMP5Navy(220, weapon = true),
	CWeaponMP7(221, weapon = true),
	CWeaponMP9(222, weapon = true),
	CWeaponNegev(223, weapon = true),
	CWeaponNOVA(224, weapon = true),
	CWeaponP228(225, weapon = true),
	CWeaponP250(226, weapon = true),
	CWeaponP90(227, weapon = true),
	CWeaponSawedoff(228, weapon = true),
	CWeaponSCAR20(229, weapon = true),
	CWeaponScout(230, weapon = true),
	CWeaponSG550(231, weapon = true),
	CWeaponSG552(232, weapon = true),
	CWeaponSG556(233, weapon = true),
	CWeaponSSG08(234, weapon = true),
	CWeaponTaser(235, weapon = true),
	CWeaponTec9(236, weapon = true),
	CWeaponTMP(237, weapon = true),
	CWeaponUMP45(238, weapon = true),
	CWeaponUSP(239, weapon = true),
	CWeaponXM1014(240, weapon = true),
	CWorld(241),
	DustTrail(242),
	MovieExplosion(243),
	ParticleSmokeGrenade(244, grenade = true),
	RocketTrail(245),
	SmokeTrail(246),
	SporeExplosion(247),
	SporeTrail(248);

	companion object {

		val cachedValues = values()

		val size = cachedValues.size

		fun byID(id: Long) = cachedValues.firstOrNull { it.id == id }

		fun byEntityAddress(address: Long): EntityType {
			val vt = (csgoEXE.read(address + 0x8, 4) ?: return NULL).getInt(0).unsign()
			val fn = (csgoEXE.read(vt + 2 * 0x4, 4) ?: return NULL).getInt(0).unsign()
			val cls = (csgoEXE.read(fn + 0x1, 4) ?: return NULL).getInt(0).unsign()
			val clsid = (csgoEXE.read(cls + 20, 4) ?: return NULL).getInt(0).unsign()
			return byID(clsid) ?: NULL
		}

	}

}